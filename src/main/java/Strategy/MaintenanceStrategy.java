package Strategy;

import DTOs.FuelEnergyLog;

public interface MaintenanceStrategy {
	 String predictMaintenance(FuelEnergyLog log);
}
