package Strategy;

import java.time.LocalDateTime;

import DTOs.FuelEnergyLog;
import DTOs.MaintenanceSchedule;
import DaoImpl.MaintenanceScheduleDAOImpl;

public class TimeAndMileageMaintenanceStrategy implements MaintenanceStrategy {
	MaintenanceScheduleDAOImpl maintenance = new MaintenanceScheduleDAOImpl();
	
	@Override
    public String predictMaintenance(FuelEnergyLog log) {
        boolean timeDue = log.getLogDate().isBefore(LocalDateTime.now().minusMonths(6));
        boolean mileageDue = log.getMileage() != null && log.getMileage().doubleValue() > 40000;

        if (timeDue || mileageDue) {
        	MaintenanceSchedule schedule = new MaintenanceSchedule();
            schedule.setVehicleId(log.getVehicleId());
            schedule.setPredictedDate(LocalDateTime.now().plusDays(14)); // e.g. 2 weeks from now
            schedule.setRecommendation("Recommend: Full service (time/mileage interval)");
            schedule.setStrategyUsed("Mileage");
            schedule.setStatus("Pending");

            maintenance.saveSchedule(schedule);
            return schedule.getRecommendation();
            
        }
        return "No maintenance needed";
    }


}

