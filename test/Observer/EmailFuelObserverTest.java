package Observer;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EmailFuelObserverTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private EmailFuelObserver observer;

    @BeforeEach
    public void setUp() {
        observer = new EmailFuelObserver();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testOnFuelLogAdded_printsCorrectMessage() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(101);
        log.setFuelConsumed(new BigDecimal("123.45"));
        log.setMileage(new BigDecimal("9876.54"));
        log.setLogDate(LocalDateTime.of(2025, 8, 7, 14, 30));
        log.setNotes("Test notes");

        observer.onFuelLogAdded(log);

        String output = outContent.toString();

        assertTrue(output.contains("📧 Sending email to admin@example.com:"));
        assertTrue(output.contains("Vehicle ID: 101"));
        assertTrue(output.contains("Fuel: 123.45"));
        assertTrue(output.contains("Mileage: 9876.54"));
        assertTrue(output.contains("Date: 2025-08-07T14:30"));
        assertTrue(output.contains("Notes: Test notes"));
    }
}
