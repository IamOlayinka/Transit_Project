package DaoImpl;

import DTOs.MaintenanceSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaintenanceScheduleDAOImplTest {

    private MaintenanceScheduleDAOImpl dao; 

    @BeforeEach
    public void setUp() {
        dao = new MaintenanceScheduleDAOImpl();
    }

    @Test
    public void testSaveSchedule() {
        MaintenanceSchedule schedule = new MaintenanceSchedule();
        schedule.setVehicleId(1);  // Make sure vehicle ID 1 exists in DB
        schedule.setPredictedDate(LocalDateTime.now().plusDays(7));
        schedule.setRecommendation("Replace filter");
        schedule.setStrategyUsed("Predictive Analytics");
        schedule.setStatus("Pending");

        boolean result = dao.saveSchedule(schedule);
        assertTrue(result, "Schedule should be saved successfully");
    }

    @Test
    public void testGetUpcomingSchedules() {
        List<MaintenanceSchedule> schedules = dao.getUpcomingSchedules();
        assertNotNull(schedules, "Upcoming schedules should not be null");
        assertTrue(schedules.size() >= 0, "Upcoming schedules size should be zero or more");

        schedules.forEach(System.out::println);
    }

    @Test
    public void testUpdateStatus() {
        // Assuming an existing schedule with id=1
        boolean updated = dao.updateStatus(1, "Completed");
        assertTrue(updated, "Status should be updated successfully");
    }

    @Test
    public void testDeleteSchedule() {
        // Assuming a schedule with id=2 to delete 
        boolean deleted = dao.deleteSchedule(2);
        assertTrue(deleted, "Schedule should be deleted successfully");
    }

    @Test
    public void testGetById() {
        MaintenanceSchedule schedule = dao.getById(1); // This will return a failed test because I don't have the ID in my DB
        assertNotNull(schedule, "Schedule should not be null");
        assertEquals(1, schedule.getId());
    }

    @Test
    public void testCountCompleted() {
        int count = dao.countCompleted();
        assertTrue(count >= 0, "Count of completed schedules should be zero or more");
    }

    @Test
    public void testGetUpcomingWithinDays() {
        List<MaintenanceSchedule> schedules = dao.getUpcomingWithinDays(30);
        assertNotNull(schedules, "Schedules within days should not be null");
        assertTrue(schedules.size() >= 0);
    }

    @Test
    public void testGetAllSchedules() {
        List<MaintenanceSchedule> schedules = dao.getAllSchedules();
        assertNotNull(schedules, "All schedules list should not be null");
        assertTrue(schedules.size() >= 0);

        schedules.forEach(System.out::println);
    }
}
