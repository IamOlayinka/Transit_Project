package Strategy;

import DTOs.FuelEnergyLog;

public class MileageBasedMaintenanceStrategy implements MaintenanceStrategy {
    @Override
    public String predictMaintenance(FuelEnergyLog log) {
        if (log.getMileage() != null && log.getMileage().doubleValue() > 10000) {
            return "Recommend: Oil change and tire rotation";
        }
        return "No maintenance needed";
    }
}