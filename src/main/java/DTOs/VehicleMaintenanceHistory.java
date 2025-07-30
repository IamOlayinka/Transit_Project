package DTOs;

import java.time.LocalDateTime;

public class VehicleMaintenanceHistory {
    private int id;
    private int vehicleId;
    private LocalDateTime maintenanceDate;
    private Double mileageAtService;
    private String notes;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public LocalDateTime getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(LocalDateTime maintenanceDate) { this.maintenanceDate = maintenanceDate; }

    public Double getMileageAtService() { return mileageAtService; }
    public void setMileageAtService(Double mileageAtService) { this.mileageAtService = mileageAtService; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
