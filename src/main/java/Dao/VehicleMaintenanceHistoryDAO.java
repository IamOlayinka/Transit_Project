package Dao;

import DTOs.VehicleMaintenanceHistory;
import java.util.Map;
import java.util.List;

public interface VehicleMaintenanceHistoryDAO {
    VehicleMaintenanceHistory getLastMaintenanceByVehicle(int vehicleId);
    boolean addMaintenanceRecord(VehicleMaintenanceHistory record);
    Map<Integer, List<VehicleMaintenanceHistory>> getHistoryGroupedByVehicle();
}

