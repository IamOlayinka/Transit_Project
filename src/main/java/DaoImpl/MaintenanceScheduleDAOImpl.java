package DaoImpl;

import DTOs.MaintenanceSchedule;
import Dao.MaintenanceScheduleDAO;
import dataaccessLayer.Datasource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceScheduleDAOImpl implements MaintenanceScheduleDAO {

    public boolean saveSchedule(MaintenanceSchedule schedule) {
        String sql = "INSERT INTO maintenance_schedule (vehicle_id, predicted_date, recommendation, strategy_used) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, schedule.getVehicleId());
            ps.setObject(2, schedule.getPredictedDate());
            ps.setString(3, schedule.getRecommendation());
            ps.setString(4, schedule.getStrategyUsed());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MaintenanceSchedule> getUpcomingSchedules() {
        List<MaintenanceSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_schedule WHERE predicted_date >= NOW() ORDER BY predicted_date ASC";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MaintenanceSchedule m = new MaintenanceSchedule();
                m.setId(rs.getInt("id"));
                m.setVehicleId(rs.getInt("vehicle_id"));
                m.setPredictedDate(rs.getTimestamp("predicted_date").toLocalDateTime());
                m.setRecommendation(rs.getString("recommendation"));
                m.setStrategyUsed(rs.getString("strategy_used"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public boolean updateStatus(int id, String newStatus) {
        String sql = "UPDATE maintenance_schedule SET status = ? WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteSchedule(int id) {
        String sql = "DELETE FROM maintenance_schedule WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MaintenanceSchedule getById(int id) {
        String sql = "SELECT * FROM maintenance_schedule WHERE id = ?";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MaintenanceSchedule schedule = new MaintenanceSchedule();
                schedule.setId(rs.getInt("id"));
                schedule.setVehicleId(rs.getInt("vehicle_id"));
                schedule.setPredictedDate(rs.getTimestamp("predicted_date").toLocalDateTime());
                schedule.setRecommendation(rs.getString("recommendation"));
                schedule.setStrategyUsed(rs.getString("strategy_used"));
                schedule.setStatus(rs.getString("status"));
                return schedule;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countCompleted() {
        String sql = "SELECT COUNT(*) FROM maintenance_schedule WHERE status = 'Completed'";
        try (Connection conn = Datasource.getInstance().getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public List<MaintenanceSchedule> getUpcomingWithinDays(int days) {
        List<MaintenanceSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_schedule WHERE predicted_date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL ? DAY)";
        try (Connection conn = Datasource.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, days);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaintenanceSchedule m = new MaintenanceSchedule();
                m.setId(rs.getInt("id"));
                m.setVehicleId(rs.getInt("vehicle_id"));
                m.setPredictedDate(rs.getTimestamp("predicted_date").toLocalDateTime());
                m.setStrategyUsed(rs.getString("strategy_used"));
                m.setRecommendation(rs.getString("recommendation"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



	
}
