package DaoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTOs.FuelEnergyLog;
import Dao.FuelEnergyLogDAO;
import Observer.EmailFuelObserver;
import Observer.FuelLogSubject;
import Observer.LowFuelAlertObserver;
import dataaccessLayer.Datasource;


public class FuelEnergyLogImp implements FuelEnergyLogDAO {

    @Override
    public boolean addFuelEnergyLog(FuelEnergyLog log) {
        String sql = "INSERT INTO fuel_energy_logs (vehicle_id, log_date, fuel_consumed, energy_consumed, mileage, notes) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, log.getVehicleId());
            ps.setTimestamp(2, Timestamp.valueOf(log.getLogDate()));
            ps.setBigDecimal(3, log.getFuelConsumed());
            ps.setBigDecimal(4, log.getEnergyConsumed());
            ps.setBigDecimal(5, log.getMileage());
            ps.setString(6, log.getNotes());

            boolean success = ps.executeUpdate() > 0;

            // ✅ Notify observers only if insert succeeded
            if (success) {
                FuelLogSubject subject = new FuelLogSubject();
                subject.register(new LowFuelAlertObserver());
                subject.register(new EmailFuelObserver());
                // Register more observers here if needed

                subject.notifyObservers(log);
            }

            return success;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FuelEnergyLog> getAllLogs() {
        List<FuelEnergyLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM fuel_energy_logs ORDER BY log_date DESC";
        try (Connection conn = Datasource.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                logs.add(mapResultSetToLog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total Logs Found: " + logs.size());
        return logs;
    }

    @Override
    public List<FuelEnergyLog> getLogsByVehicle(int vehicleId) {
        List<FuelEnergyLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM fuel_energy_logs WHERE vehicle_id = ? ORDER BY log_date DESC";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(mapResultSetToLog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public boolean updateFuelEnergyLog(FuelEnergyLog log) {
        String sql = "UPDATE fuel_energy_logs SET vehicle_id = ?, log_date = ?, fuel_consumed = ?, energy_consumed = ?, mileage = ?, notes = ? WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getVehicleId());
            ps.setTimestamp(2, Timestamp.valueOf(log.getLogDate()));
            ps.setBigDecimal(3, log.getFuelConsumed());
            ps.setBigDecimal(4, log.getEnergyConsumed());
            ps.setBigDecimal(5, log.getMileage());
            ps.setString(6, log.getNotes());
            ps.setInt(7, log.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteFuelEnergyLog(int id) {
        String sql = "DELETE FROM fuel_energy_logs WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public FuelEnergyLog getLogById(int id) {
        String sql = "SELECT * FROM fuel_energy_logs WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToLog(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FuelEnergyLog getLatestLogByVehicle(int vehicleId) {
        String sql = "SELECT * FROM fuel_energy_log WHERE vehicle_id = ? ORDER BY log_date DESC LIMIT 1";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FuelEnergyLog log = new FuelEnergyLog();
                log.setId(rs.getInt("id"));
                log.setVehicleId(rs.getInt("vehicle_id"));
                log.setLogDate(rs.getTimestamp("log_date").toLocalDateTime());
                log.setFuelConsumed(rs.getBigDecimal("fuel_consumed"));
                log.setEnergyConsumed(rs.getBigDecimal("energy_consumed"));
                log.setMileage(rs.getBigDecimal("mileage"));
                log.setNotes(rs.getString("notes"));
                return log;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countLogs() {
        String sql = "SELECT COUNT(*) FROM fuel_energy_log";
        try (Connection conn = Datasource.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
 
    @Override
    public Map<Integer, List<FuelEnergyLog>> getLogsGroupedByVehicle() {
        Map<Integer, List<FuelEnergyLog>> groupedLogs = new HashMap<>();

        String sql = "SELECT * FROM fuel_energy_log ORDER BY vehicle_id, log_date";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FuelEnergyLog log = new FuelEnergyLog();
              
                log.setId(rs.getInt("id"));
                log.setVehicleId(rs.getInt("vehicle_id"));
                log.setLogDate(rs.getTimestamp("log_date").toLocalDateTime());
                
                log.setFuelConsumed(rs.getBigDecimal("fuel_consumed"));
                log.setEnergyConsumed(rs.getBigDecimal("energy_consumed"));
                log.setMileage(rs.getBigDecimal("mileage"));
                log.setNotes(rs.getString("notes"));

                int vehicleId = log.getVehicleId();
                groupedLogs.computeIfAbsent(vehicleId, k -> new ArrayList<>()).add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupedLogs;
    }

    
    private FuelEnergyLog mapResultSetToLog(ResultSet rs) throws SQLException {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setId(rs.getInt("id"));
        log.setVehicleId(rs.getInt("vehicle_id"));
        log.setLogDate(rs.getTimestamp("log_date").toLocalDateTime());
        log.setFuelConsumed(rs.getBigDecimal("fuel_consumed"));
        log.setEnergyConsumed(rs.getBigDecimal("energy_consumed"));
        log.setMileage(rs.getBigDecimal("mileage"));
        log.setNotes(rs.getString("notes"));
        return log;
    }

	
}
