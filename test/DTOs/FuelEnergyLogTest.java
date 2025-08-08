package DTOs;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FuelEnergyLogTest {

    @Test
    public void testGettersAndSetters() {
        FuelEnergyLog log = new FuelEnergyLog();

        int id = 123;
        int vehicleId = 456;
        LocalDateTime logDate = LocalDateTime.now();
        BigDecimal fuelConsumed = new BigDecimal("12.34");
        BigDecimal energyConsumed = new BigDecimal("56.78");
        BigDecimal mileage = new BigDecimal("100.5");
        String notes = "Test notes";

        log.setId(id);
        log.setVehicleId(vehicleId);
        log.setLogDate(logDate);
        log.setFuelConsumed(fuelConsumed);
        log.setEnergyConsumed(energyConsumed);
        log.setMileage(mileage);
        log.setNotes(notes);

        assertEquals(id, log.getId());
        assertEquals(vehicleId, log.getVehicleId());
        assertEquals(logDate, log.getLogDate());
        assertEquals(fuelConsumed, log.getFuelConsumed());
        assertEquals(energyConsumed, log.getEnergyConsumed());
        assertEquals(mileage, log.getMileage());
        assertEquals(notes, log.getNotes());
    }
}
