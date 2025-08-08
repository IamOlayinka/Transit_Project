package Observer;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LowFuelAlertObserverTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private LowFuelAlertObserver observer;

    @BeforeEach
    public void setUp() {
        observer = new LowFuelAlertObserver();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testLowFuelAlertTriggered() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(123);
        log.setFuelConsumed(new BigDecimal("4.99"));  // below 5.0
        log.setLogDate(LocalDateTime.now());

        observer.onFuelLogAdded(log);

        String output = outContent.toString().trim();
        assertTrue(output.contains("⚠️ Low fuel detected for Vehicle ID: 123"));
    }

    @Test
    public void testNoAlertWhenFuelAboveThreshold() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(456);
        log.setFuelConsumed(new BigDecimal("5.01"));  // above 5.0
        log.setLogDate(LocalDateTime.now());

        observer.onFuelLogAdded(log);

        String output = outContent.toString().trim();
        assertEquals("", output, "No output expected when fuel consumption is above threshold");
    }

    @Test
    public void testNoAlertWhenFuelConsumedIsNull() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(789);
        log.setFuelConsumed(null);
        log.setLogDate(LocalDateTime.now());

        observer.onFuelLogAdded(log);

        String output = outContent.toString().trim();
        assertEquals("", output, "No output expected when fuelConsumed is null");
    }
}
