package DaoImpl;
import java.sql.*;

import DTOs.VehicleMaintenanceHistory;
import Dao.VehicleMaintenanceHistoryDAO;
import dataaccessLayer.Datasource;


public class VehicleMaintenanceHistoryDAOImpl implements VehicleMaintenanceHistoryDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "password");
    }

    @Override
    public VehicleMaintenanceHistory getLastMaintenanceByVehicle(int vehicleId) {
        String sql = "SELECT * FROM vehicle_maintenance_history WHERE vehicle_id = ? ORDER BY maintenance_date DESC LIMIT 1";
        try (Connection conn = Datasource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
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
    public boolean addMaintenanceRecord(VehicleMaintenanceHistory record) {
        String sql = "INSERT INTO vehicle_maintenance_history (vehicle_id, maintenance_date, mileage_at_service, notes) VALUES (?, ?, ?, ?)";
        try (Connection conn = Datasource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
