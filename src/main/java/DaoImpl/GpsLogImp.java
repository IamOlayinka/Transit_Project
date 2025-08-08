package DaoImpl;


import Builder.GpsLog;
import Dao.GpsLogDao;
import dataaccessLayer.Datasource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GpsLogImp implements GpsLogDao {

	 public boolean saveGpsLog(GpsLog log) {
	        String sql = "INSERT INTO gps_logs (vehicle_id, station_name, arrival_time, departure_time, logged_by) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = Datasource.getInstance().getConnection();
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
	        
	        @Override
	        public List<GpsLog> getAllLogs() {
	        	List<GpsLog> list = new ArrayList<>();
	        	String sq1 = "SELECT * FROM gps_logs";
	        	
	            try (Connection conn = Datasource.getInstance().getConnection();
	                    PreparedStatement ps = conn.prepareStatement(sq1);
	                    ResultSet rs = ps.executeQuery()) {

	                   while (rs.next()) {
	                       GpsLog log = new GpsLog(
	                           rs.getInt("id"),
	                           rs.getInt("vehicle_id"),
	                           rs.getString("station_name"),
	                           rs.getTimestamp("arrival_time").toLocalDateTime(),
	                           rs.getTimestamp("departure_time").toLocalDateTime(),
	                           rs.getInt("logged_by")
	                       );
	                       list.add(log);
	                   }

	               } catch (SQLException e) {
	                   e.printStackTrace();
	               }
	               return list;
	        	
	        }
	    }

