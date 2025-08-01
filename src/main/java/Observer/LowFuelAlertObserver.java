package Observer;

import DTOs.FuelEnergyLog;

public class LowFuelAlertObserver implements FuelLogObserver {
    @Override
    public void onFuelLogAdded(FuelEnergyLog log) {
        if (log.getFuelConsumed() != null && log.getFuelConsumed().doubleValue() < 5.0) {
            System.out.println("⚠️ Low fuel detected for Vehicle ID: " + log.getVehicleId());
        }
    }
}
