package Strategy;

import java.time.LocalDateTime;

import DTOs.FuelEnergyLog;
import DTOs.MaintenanceSchedule;
import DaoImpl.MaintenanceScheduleDAOImpl;

public class FuelUsageMaintenanceStrategy implements MaintenanceStrategy {
	MaintenanceScheduleDAOImpl maintenance = new MaintenanceScheduleDAOImpl();
	
	@Override
    public String predictMaintenance(FuelEnergyLog log) {
        
    	if (log.getFuelConsumed() != null && log.getFuelConsumed().doubleValue() > 500) {
    	MaintenanceSchedule schedule = new MaintenanceSchedule();
        schedule.setVehicleId(log.getVehicleId());
        schedule.setPredictedDate(LocalDateTime.now().plusDays(14)); // e.g. 2 weeks from now
        schedule.setRecommendation("Schedule maintenance:Recommend: Fuel system cleaning");
        schedule.setStrategyUsed("Mileage");
        schedule.setStatus("Pending");

        maintenance.saveSchedule(schedule);
        return schedule.getRecommendation();
    	
            
        }
        return "No maintenance needed";
    }
}
