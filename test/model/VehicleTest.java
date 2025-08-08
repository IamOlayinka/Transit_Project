package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void testConstructorAndGetters() {
        Vehicle vehicle = new Vehicle(
            1,
            "ABC123",
            "Bus",
            "Diesel",
            8.5,
            50,
            "Route 66"
        );

        assertEquals(1, vehicle.getId());
        assertEquals("ABC123", vehicle.getVehicleNumber());
        assertEquals("Bus", vehicle.getVehicleType());
        assertEquals("Diesel", vehicle.getFuelType());
        assertEquals(8.5, vehicle.getConsumptionRate());
        assertEquals(50, vehicle.getMaxPassengers());
        assertEquals("Route 66", vehicle.getAssignedRoute());
    }
}
