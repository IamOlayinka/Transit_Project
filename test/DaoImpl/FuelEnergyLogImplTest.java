package DaoImpl;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import DaoImpl.FuelEnergyLogImp;
import DTOs.FuelEnergyLog;

public class FuelEnergyLogImplTest { 

    private final FuelEnergyLogImp dao = new FuelEnergyLogImp();

    @Test
    public void testAddFuelEnergyLog() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setVehicleId(1); // Use a valid vehicle ID from your DB
        log.setLogDate(LocalDateTime.now());
        log.setFuelConsumed(new BigDecimal("12.5"));
        log.setEnergyConsumed(new BigDecimal("3.4"));
        log.setMileage(new BigDecimal("110.7"));
        log.setNotes("JUnit test entry");

        boolean result = dao.addFuelEnergyLog(log);
        assertTrue(result, "Log should be added successfully");
    }
}
