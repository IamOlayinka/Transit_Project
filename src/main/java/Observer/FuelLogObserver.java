package Observer;

import DTOs.FuelEnergyLog;

public interface FuelLogObserver {
    void onFuelLogAdded(FuelEnergyLog log);
}