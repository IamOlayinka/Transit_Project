package DTOs;

import java.time.LocalDateTime;

public class MaintenanceSchedule {
    private int id;
    private int vehicleId;
    private LocalDateTime predictedDate;
    private String recommendation;
    private String strategyUsed;
    private String status;

    
    
    //Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public LocalDateTime getPredictedDate() { return predictedDate; }
    public void setPredictedDate(LocalDateTime predictedDate) { this.predictedDate = predictedDate; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    public String getStrategyUsed() { return strategyUsed; }
    public void setStrategyUsed(String strategyUsed) { this.strategyUsed = strategyUsed; }
    
    
}