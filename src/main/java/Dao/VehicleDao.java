package Dao;

import java.util.List;

import model.Vehicle;

public interface VehicleDao {

	boolean registerVehicle(Vehicle vehicle);
	List<Vehicle> getAllVehicles();
	Vehicle getVehicleById(int id);
	boolean updateVehicle(Vehicle v);
	boolean deleteVehicle(int id);
	int countVehicles();
	int countVehiclesByUserID(int userID);
	List<Vehicle> getVehiclesByAssignedUserID(int assignedUserId);
}