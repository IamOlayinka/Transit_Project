package DaoImpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTOs.VehicleMaintenanceHistory;
import Dao.VehicleMaintenanceHistoryDAO;
import dataaccessLayer.Datasource;


public class VehicleMaintenanceHistoryDAOImpl implements VehicleMaintenanceHistoryDAO {

//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ptfms", "cst8288", "ct8288");
//    }

    @Override
    public VehicleMaintenanceHistory getLastMaintenanceByVehicle(int vehicleId) {
        String sql = "SELECT * FROM vehicle_maintenance_history WHERE vehicle_id = ? ORDER BY maintenance_date DESC LIMIT 1";
        try (Connection conn = Datasource.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                VehicleMaintenanceHistory history = new VehicleMaintenanceHistory();
                history.setId(rs.getInt("id"));
                history.setVehicleId(rs.getInt("vehicle_id"));
                history.setMaintenanceDate(rs.getTimestamp("maintenance_date").toLocalDateTime());
                history.setMileageAtService(rs.getDouble("mileage_at_service"));
                history.setNotes(rs.getString("notes"));
                return history;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Map<Integer, List<VehicleMaintenanceHistory>> getHistoryGroupedByVehicle() {
        Map<Integer, List<VehicleMaintenanceHistory>> grouped = new HashMap<>();

        String sql = "SELECT * FROM vehicle_maintenance_history ORDER BY vehicle_id, maintenance_date";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VehicleMaintenanceHistory history = new VehicleMaintenanceHistory();
                history.setId(rs.getInt("id"));
                history.setVehicleId(rs.getInt("vehicle_id"));
                history.setMaintenanceDate(rs.getTimestamp("maintenance_date").toLocalDateTime());
                history.setMileageAtService(rs.getDouble("mileage_at_service"));
                history.setNotes(rs.getString("notes"));

                int vehicleId = history.getVehicleId();
                grouped.computeIfAbsent(vehicleId, k -> new ArrayList<>()).add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grouped;
    }

    @Override
    public boolean addMaintenanceRecord(VehicleMaintenanceHistory record) {
        String sql = "INSERT INTO vehicle_maintenance_history (vehicle_id, maintenance_date, mileage_at_service, notes) VALUES (?, ?, ?, ?)";
        try (Connection conn = Datasource.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, record.getVehicleId());
            ps.setObject(2, record.getMaintenanceDate());
            ps.setObject(3, record.getMileageAtService());
            ps.setString(4, record.getNotes());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	
}
