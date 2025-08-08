package Observer;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FuelLogObserverTest {

    @Test
    public void testOnFuelLogAdded_implementationCalled() {
        FuelEnergyLog sampleLog = new FuelEnergyLog();
        sampleLog.setVehicleId(1);
        sampleLog.setFuelConsumed(new BigDecimal("10.5"));
        sampleLog.setMileage(new BigDecimal("2000"));
        sampleLog.setLogDate(LocalDateTime.now());
        sampleLog.setNotes("Test");

        // Anonymous implementation to test the method is called with correct input
        FuelLogObserver observer = new FuelLogObserver() {
            boolean called = false;
            @Override
            public void onFuelLogAdded(FuelEnergyLog log) {
                called = true;
                assertEquals(1, log.getVehicleId());
                assertEquals(new BigDecimal("10.5"), log.getFuelConsumed());
                assertEquals("Test", log.getNotes());
            }
        };

        observer.onFuelLogAdded(sampleLog);
        // Since 'called' is local to anonymous class, can't assert here directly,
        // so this test primarily checks the assertions inside onFuelLogAdded run without error.
    }
}
