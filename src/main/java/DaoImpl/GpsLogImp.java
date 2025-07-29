package DaoImpl;


import Builder.GpsLog;
import Dao.GpsLogDao;
import dataaccessLayer.Datasource;

import java.sql.*;
import java.time.LocalDateTime;

public class GpsLogImp implements GpsLogDao {

	 public boolean saveGpsLog(GpsLog log) {
	        String sql = "INSERT INTO gps_logs (vehicle_id, station_name, arrival_time, departure_time, logged_by) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = Datasource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, log.getVehicleId());
	            stmt.setString(2, log.getStationName());
	            stmt.setTimestamp(3, Timestamp.valueOf(log.getArrivalTime()));
	            stmt.setTimestamp(4, Timestamp.valueOf(log.getDepartureTime()));
	            stmt.setInt(5, log.getLoggedBy());

	            return stmt.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
