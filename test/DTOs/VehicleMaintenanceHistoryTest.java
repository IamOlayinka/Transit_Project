package DTOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleMaintenanceHistoryTest {

    @Test
    public void testGettersAndSetters() {
        VehicleMaintenanceHistory history = new VehicleMaintenanceHistory();

        int id = 10;
        int vehicleId = 20;
        LocalDateTime maintenanceDate = LocalDateTime.now();
        Double mileageAtService = 12345.67;
        String notes = "Changed oil and filters";
        String recommendation = "Check brakes soon";
        String strategyUsed = "Preventive";
        String status = "Completed";

        history.setId(id);
        history.setVehicleId(vehicleId);
        history.setMaintenanceDate(maintenanceDate);
        history.setMileageAtService(mileageAtService);
        history.setNotes(notes);
        history.setRecommendation(recommendation);
        history.setStrategyUsed(strategyUsed);
        history.setStatus(status);

        assertEquals(id, history.getId());
        assertEquals(vehicleId, history.getVehicleId());
        assertEquals(maintenanceDate, history.getMaintenanceDate());
        assertEquals(mileageAtService, history.getMileageAtService());
        assertEquals(notes, history.getNotes());
        assertEquals(recommendation, history.getRecommendation());
        assertEquals(strategyUsed, history.getStrategyUsed());
        assertEquals(status, history.getStatus());
    }
}
