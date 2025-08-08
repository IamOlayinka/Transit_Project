package DaoImpl;

import DTOs.VehicleMaintenanceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleMaintenanceHistoryDAOImplTest {

    private VehicleMaintenanceHistoryDAOImpl dao;

    @BeforeEach
    public void setUp() {
        dao = new VehicleMaintenanceHistoryDAOImpl();
    }

    @Test
    public void testAddMaintenanceRecord() {
        VehicleMaintenanceHistory record = new VehicleMaintenanceHistory();
        record.setVehicleId(1); // Ensure this vehicle ID exists in DB
        record.setMaintenanceDate(LocalDateTime.now());
        record.setMileageAtService(12345.6);
        record.setNotes("Oil change and tire rotation");

        boolean result = dao.addMaintenanceRecord(record);
        assertTrue(result, "Maintenance record should be added successfully");
    }

    @Test
    public void testGetLastMaintenanceByVehicle() {
        int vehicleId = 1; // Valid vehicle ID with history in DB
        VehicleMaintenanceHistory history = dao.getLastMaintenanceByVehicle(vehicleId);
        assertNotNull(history, "Last maintenance history should not be null");
        assertEquals(vehicleId, history.getVehicleId());
    }

    @Test
    public void testGetHistoryGroupedByVehicle() {
        Map<Integer, List<VehicleMaintenanceHistory>> grouped = dao.getHistoryGroupedByVehicle();
        assertNotNull(grouped, "Grouped history map should not be null");
        assertTrue(grouped.size() >= 0, "Grouped history size should be zero or more");

        grouped.forEach((vehicleId, histories) -> {
            assertNotNull(histories, "Histories list should not be null");
            assertTrue(histories.size() >= 0);
            // Optional printout for debugging
            System.out.println("Vehicle ID: " + vehicleId + ", Records: " + histories.size());
        });
    }
}
