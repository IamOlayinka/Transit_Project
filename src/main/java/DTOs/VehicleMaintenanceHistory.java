package DTOs;

import java.time.LocalDateTime;

public class VehicleMaintenanceHistory {
    private int id;
    private int vehicleId;
    private LocalDateTime maintenanceDate;
    private Double mileageAtService;
    private String notes;
    private String recommendation;  
    private String strategyUsed;
    private String status;

    public String getStrategyUsed() {
        return strategyUsed;
    }

    public void setStrategyUsed(String strategyUsed) {
        this.strategyUsed = strategyUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // Getters and setters
    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

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
