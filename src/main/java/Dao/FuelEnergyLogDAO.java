package Dao;

import java.util.List;
import java.util.Map;

import DTOs.FuelEnergyLog;

public interface FuelEnergyLogDAO {

    boolean addFuelEnergyLog(FuelEnergyLog log);
    List<FuelEnergyLog> getLogsByVehicle(int vehicleId);
    boolean updateFuelEnergyLog(FuelEnergyLog log);
    boolean deleteFuelEnergyLog(int id);
	List<FuelEnergyLog> getAllLogs();
	FuelEnergyLog getLogById(int id);
	FuelEnergyLog getLatestLogByVehicle(int vehicleId);
	int countLogs();
	int countLogsByUserID(int userID);
	Map<Integer, List<FuelEnergyLog>> getLogsGroupedByVehicle();
}
