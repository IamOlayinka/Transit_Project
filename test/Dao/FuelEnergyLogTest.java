package Dao;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FuelEnergyLogTest {

    @Test
    public void testSetAndGetFuelConsumed() {
        FuelEnergyLog log = new FuelEnergyLog();
        log.setFuelConsumed(new BigDecimal("15.5"));

        assertEquals(new BigDecimal("15.5"), log.getFuelConsumed());
    }

    @Test
    public void testSetAndGetLogDate() {
        FuelEnergyLog log = new FuelEnergyLog();
        LocalDateTime now = LocalDateTime.now();
        log.setLogDate(now);

        assertEquals(now, log.getLogDate());
    }
}
