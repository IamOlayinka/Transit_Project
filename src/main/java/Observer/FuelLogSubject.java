package Observer;

import java.util.ArrayList;
import java.util.List;

import DTOs.FuelEnergyLog;

public class FuelLogSubject {
    private final List<FuelLogObserver> observers = new ArrayList<>();

    public void register(FuelLogObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(FuelEnergyLog log) {
        for (FuelLogObserver observer : observers) {
            observer.onFuelLogAdded(log);
        }
    }
}
