package Strategy;

import java.time.LocalDateTime;

import DTOs.FuelEnergyLog;

public class TimeAndMileageMaintenanceStrategy implements MaintenanceStrategy {
    @Override
    public String predictMaintenance(FuelEnergyLog log) {
        boolean timeDue = log.getLogDate().isBefore(LocalDateTime.now().minusMonths(6));
        boolean mileageDue = log.getMileage() != null && log.getMileage().doubleValue() > 40000;

        if (timeDue || mileageDue) {
            return "Recommend: Full service (time/mileage interval)";
        }
        return "No maintenance needed";
    }


}

