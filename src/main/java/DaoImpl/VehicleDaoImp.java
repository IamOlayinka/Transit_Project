package DaoImpl;
import model.Vehicle;
import Dao.VehicleDao;
import dataaccessLayer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImp implements VehicleDao {


	    public boolean registerVehicle(Vehicle vehicle)  {
	        String sql = "INSERT INTO vehicles (vehicle_number, vehicle_type, fuel_type, consumption_rate, max_passengers, assigned_route, assigned_user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try 
	         {
	        	//Creating connection with the database
	        	Connection conn = Datasource.getInstance().getConnection();
	        	
	        	//Querying the database
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            
	            //Setting the query value using the vehicle variables
	            
	            stmt.setString(1, vehicle.getVehicleNumber());
	            stmt.setString(2, vehicle.getVehicleType());
	            stmt.setString(3, vehicle.getFuelType());
	            stmt.setDouble(4, vehicle.getConsumptionRate());
	            stmt.setInt(5, vehicle.getMaxPassengers());
	            stmt.setString(6, vehicle.getAssignedRoute());
	            stmt.setInt(7, vehicle.getAssignedUserID());

	            return stmt.executeUpdate() > 0;

	        } catch (SQLException e) {
	            e.printStackTrace(); // Consider using a logger in production
	            return false;
	        }
	    }

		@Override
		public List<Vehicle> getAllVehicles() {
			List<Vehicle> vehicleList = new ArrayList<>();
		    String sql = "SELECT * FROM vehicles";

		    try (Connection conn = Datasource.getInstance().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		    	
                System.out.println(rs);
		        while (rs.next()) {
		        	int id = rs.getInt("id");
		        	String VehicleNumber = rs.getString("vehicle_number");
		        	String VehicleType = rs.getString("vehicle_type");
		        	String FuelType = rs.getString("fuel_type");
		        	double ConsumptionRate = rs.getDouble("consumption_rate");
		        	int MaxPassengers = rs.getInt("max_passengers");
		        	String AssignedRoute = rs.getString("assigned_route");
		        	int AssignedUserID = rs.getInt("assigned_user_id");		        	
		            Vehicle vehicle = new Vehicle(id,VehicleNumber,VehicleType,FuelType,ConsumptionRate,MaxPassengers,AssignedRoute, AssignedUserID);
		               
		             
		            vehicleList.add(vehicle);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    
		    return vehicleList;
		}

		@Override
		public Vehicle getVehicleById(int id) {
			String sql = "SELECT * FROM vehicles WHERE id = ?";
		    try (Connection conn = Datasource.getInstance().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            return new Vehicle(
		                rs.getInt("id"),
		                rs.getString("vehicle_number"),
		                rs.getString("vehicle_type"),
		                rs.getString("fuel_type"),
		                rs.getDouble("consumption_rate"),
		                rs.getInt("max_passengers"),
		                rs.getString("assigned_route"),
		                rs.getInt("assigned_user_id")
		            );
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return null;
		}

		@Override
		public boolean updateVehicle(Vehicle v) {
			String sql = "UPDATE vehicles SET vehicle_number=?, vehicle_type=?, fuel_type=?, consumption_rate=?, max_passengers=?, assigned_route=?, assigned_user_id=? WHERE id=?";
		    try (Connection conn = Datasource.getInstance().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, v.getVehicleNumber());
		        stmt.setString(2, v.getVehicleType());
		        stmt.setString(3, v.getFuelType());
		        stmt.setDouble(4, v.getConsumptionRate());
		        stmt.setInt(5, v.getMaxPassengers());
		        stmt.setString(6, v.getAssignedRoute());
		        stmt.setInt(7, v.getAssignedUserID());
		        stmt.setInt(8, v.getId());
		        return stmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		@Override
		public boolean deleteVehicle(int id) {
			    String sql = "DELETE FROM vehicles WHERE id = ?";
			    try (Connection conn = Datasource.getInstance().getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {
			        stmt.setInt(1, id);
			        return stmt.executeUpdate() > 0;
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}

		public int countVehicles() {
			 String sql = "SELECT COUNT(*) FROM vehicles";
			    try (Connection conn = Datasource.getInstance().getConnection(); Statement stmt = conn.createStatement()) {
			        ResultSet rs = stmt.executeQuery(sql);
			        if (rs.next()) return rs.getInt(1);
			    } catch (SQLException e) { e.printStackTrace(); }
			    return 0;
		}
		
//		@Override
//	    public String getAssignedUserName(int id){
//	        String assignedUserName = null;
//	        String sql = "SELECT u.name FROM Vehicles v JOIN Users u ON v.assigned_to = u.user_id WHERE v.vehicle_id = ?";
//	
//	        try (Connection conn = Datasource.getConnection();
//	             PreparedStatement ps = conn.prepareStatement(sql)) {
//	            ps.setInt(1, id);
//	            ResultSet rs = ps.executeQuery();
//	            if (rs.next()) {
//	                assignedUserName = rs.getString("name");
//	            }
//	        }
//	        return assignedUserName;
//	    }
		
			


}
