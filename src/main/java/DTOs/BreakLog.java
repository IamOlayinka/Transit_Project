package DTOs;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Break Log
 * Represents operator breaks and out-of-service periods
 * 
 * @author [Your Name]
 */
public class BreakLog {
    private int id;
	private int operatorId;
    private Integer vehicleId; // Nullable - operator might not be assigned to vehicle during break
    private LocalDateTime breakStartTime;
    private LocalDateTime breakEndTime; // Nullable for active/ongoing breaks
    private int duration;
    private String breakType; // "break" or "out_of_service"
    private String notes;
    private LocalDateTime createdAt;
	
	
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}


	public Integer getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}


	public LocalDateTime getBreakStartTime() {
		return breakStartTime;
	}


	public void setBreakStartTime(LocalDateTime breakStartTime) {
		this.breakStartTime = breakStartTime;
	}


	public LocalDateTime getBreakEndTime() {
		return breakEndTime;
	}


	public void setBreakEndTime(LocalDateTime breakEndTime) {
		this.breakEndTime = breakEndTime;
	}
	
	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getBreakType() {
		return breakType;
	}


	public void setBreakType(String breakType) {
		this.breakType = breakType;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "BreakLog [id=" + id + ", operatorId=" + operatorId + ", vehicleId=" + vehicleId + ", breakStartTime="
				+ breakStartTime + ", breakEndTime=" + breakEndTime + ", breakType=" + breakType + ", notes=" + notes
				+ ", createdAt=" + createdAt + "]";
	}
 
}