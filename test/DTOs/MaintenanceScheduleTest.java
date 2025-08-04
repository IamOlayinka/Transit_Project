package DTOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceScheduleTest {

    @Test
    void testMaintenanceScheduleGettersAndSetters() {
        MaintenanceSchedule schedule = new MaintenanceSchedule();

        int testId = 1;
        int testVehicleId = 101;
        LocalDateTime testDate = LocalDateTime.of(2025, 8, 4, 12, 0);
        String testRecommendation = "Replace brake pads";
        String testStrategyUsed = "Predictive";
        String testStatus = "Scheduled";

        schedule.setId(testId);
        schedule.setVehicleId(testVehicleId);
        schedule.setPredictedDate(testDate);
        schedule.setRecommendation(testRecommendation);
        schedule.setStrategyUsed(testStrategyUsed);
        schedule.setStatus(testStatus);

        assertEquals(testId, schedule.getId());
        assertEquals(testVehicleId, schedule.getVehicleId());
        assertEquals(testDate, schedule.getPredictedDate());
        assertEquals(testRecommendation, schedule.getRecommendation());
        assertEquals(testStrategyUsed, schedule.getStrategyUsed());
        assertEquals(testStatus, schedule.getStatus());
    }
}
