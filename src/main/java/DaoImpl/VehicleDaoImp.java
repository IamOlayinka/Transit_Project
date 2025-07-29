package DaoImpl;
import model.Vehicle;
import Dao.VehicleDao;
import dataaccessLayer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImp implements VehicleDao {


	    public boolean registerVehicle(Vehicle vehicle)  {
	        String sql = "INSERT INTO vehicles (vehicle_number, vehicle_type, fuel_type, consumption_rate, max_passengers, assigned_route) VALUES (?, ?, ?, ?, ?, ?)";

	        try 
	         {
	        	//Creating connection with the database
	        	Connection conn = Datasource.getConnection();
	        	
	        	//Querying the database
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            
	            //Setting the query value using the vehicle variables
	            
	            stmt.setString(1, vehicle.getVehicleNumber());
	            stmt.setString(2, vehicle.getVehicleType());
	            stmt.setString(3, vehicle.getFuelType());
	            stmt.setDouble(4, vehicle.getConsumptionRate());
	            stmt.setInt(5, vehicle.getMaxPassengers());
	            stmt.setString(6, vehicle.getAssignedRoute());

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

		    try (Connection conn = Datasource.getConnection();
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
		            Vehicle vehicle = new Vehicle(id,VehicleNumber,VehicleType,FuelType,ConsumptionRate,MaxPassengers,AssignedRoute);
		               
		             
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
		    try (Connection conn = Datasource.getConnection();
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
		                rs.getString("assigned_route")
		            );
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return null;
		}

		@Override
		public boolean updateVehicle(Vehicle v) {
			String sql = "UPDATE vehicles SET vehicle_number=?, vehicle_type=?, fuel_type=?, consumption_rate=?, max_passengers=?, assigned_route=? WHERE id=?";
		    try (Connection conn = Datasource.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, v.getVehicleNumber());
		        stmt.setString(2, v.getVehicleType());
		        stmt.setString(3, v.getFuelType());
		        stmt.setDouble(4, v.getConsumptionRate());
		        stmt.setInt(5, v.getMaxPassengers());
		        stmt.setString(6, v.getAssignedRoute());
		        stmt.setInt(7, v.getId());
		        return stmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		@Override
		public boolean deleteVehicle(int id) {
			    String sql = "DELETE FROM vehicles WHERE id = ?";
			    try (Connection conn = Datasource.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {
			        stmt.setInt(1, id);
			        return stmt.executeUpdate() > 0;
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}
			


}
