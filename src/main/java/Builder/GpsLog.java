package Builder;


import java.time.LocalDateTime;

public class GpsLog {
    private int id;
    private int vehicleId;
    private String stationName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int loggedBy;

    public GpsLog(int id, int vehicleId, String stationName, LocalDateTime arrivalTime, LocalDateTime departureTime, int loggedBy) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.loggedBy = loggedBy;
    }

    // Getters only (optional setters depending on needs)
    public int getId() { return id; }
    public int getVehicleId() { return vehicleId; }
    public String getStationName() { return stationName; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public int getLoggedBy() { return loggedBy; }
}
