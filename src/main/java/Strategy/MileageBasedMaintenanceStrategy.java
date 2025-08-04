package Strategy;

import java.time.LocalDateTime;

import DTOs.FuelEnergyLog;
import DTOs.MaintenanceSchedule;
import Dao.MaintenanceScheduleDAO;
import DaoImpl.MaintenanceScheduleDAOImpl;

public class MileageBasedMaintenanceStrategy implements MaintenanceStrategy {
	MaintenanceScheduleDAOImpl maintenance = new MaintenanceScheduleDAOImpl();
	@Override
    public String predictMaintenance(FuelEnergyLog log) {
        if (log.getMileage() != null && log.getMileage().doubleValue() > 10000) {
        	MaintenanceSchedule schedule = new MaintenanceSchedule();
            schedule.setVehicleId(log.getVehicleId());
            schedule.setPredictedDate(LocalDateTime.now().plusDays(14)); // e.g. 2 weeks from now
            schedule.setRecommendation("Schedule maintenance: mileage over 40,000 km, Recommend: Oil change and tire rotation");
            schedule.setStrategyUsed("Mileage");
            schedule.setStatus("Pending");

            maintenance.saveSchedule(schedule);
            return schedule.getRecommendation();
            
        }
        return "No maintenance needed";
    }
}