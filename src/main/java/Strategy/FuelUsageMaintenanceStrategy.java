package Strategy;

import DTOs.FuelEnergyLog;

public class FuelUsageMaintenanceStrategy implements MaintenanceStrategy {
    @Override
    public String predictMaintenance(FuelEnergyLog log) {
        if (log.getFuelConsumed() != null && log.getFuelConsumed().doubleValue() > 500) {
            return "Recommend: Fuel system cleaning";
        }
        return "No maintenance needed";
    }
}
