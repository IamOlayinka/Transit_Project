package Dao;

import DTOs.VehicleMaintenanceHistory;

public interface VehicleMaintenanceHistoryDAO {
    VehicleMaintenanceHistory getLastMaintenanceByVehicle(int vehicleId);
    boolean addMaintenanceRecord(VehicleMaintenanceHistory record);
}

