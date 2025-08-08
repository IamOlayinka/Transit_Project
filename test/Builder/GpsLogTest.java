package Builder;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GpsLogTest {

    @Test
    public void testConstructorAndGetters() {
        int id = 1;
        int vehicleId = 101;
        String stationName = "Central Station";
        LocalDateTime arrival = LocalDateTime.of(2025, 8, 7, 10, 30);
        LocalDateTime departure = LocalDateTime.of(2025, 8, 7, 10, 45);
        int loggedBy = 5;

        GpsLog log = new GpsLog(id, vehicleId, stationName, arrival, departure, loggedBy);

        assertEquals(id, log.getId());
        assertEquals(vehicleId, log.getVehicleId());
        assertEquals(stationName, log.getStationName());
        assertEquals(arrival, log.getArrivalTime());
        assertEquals(departure, log.getDepartureTime());
        assertEquals(loggedBy, log.getLoggedBy());
    }
}
