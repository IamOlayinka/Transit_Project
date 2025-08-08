package Observer;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FuelLogSubjectTest {

    private FuelLogSubject subject;
    private TestObserver observer1;
    private TestObserver observer2;

    // A simple test observer implementation to track calls
    static class TestObserver implements FuelLogObserver {
        boolean notified = false;
        FuelEnergyLog receivedLog = null;

        @Override
        public void onFuelLogAdded(FuelEnergyLog log) {
            notified = true;
            receivedLog = log;
        }
    }

    @BeforeEach
    public void setup() {
        subject = new FuelLogSubject();
        observer1 = new TestObserver();
        observer2 = new TestObserver();
    }

    @Test
    public void testRegisterAndNotifyObservers() {
        subject.register(observer1);
        subject.register(observer2);

        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(10);
        log.setFuelConsumed(new BigDecimal("20.5"));
        log.setMileage(new BigDecimal("1500"));
        log.setLogDate(LocalDateTime.now());
        log.setNotes("Test log");

        subject.notifyObservers(log);

        assertTrue(observer1.notified, "Observer1 should be notified");
        assertEquals(log, observer1.receivedLog, "Observer1 should receive the correct log");

        assertTrue(observer2.notified, "Observer2 should be notified");
        assertEquals(log, observer2.receivedLog, "Observer2 should receive the correct log");
    }
}
