package DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FuelEnergyLog {
    private int id;
    private int vehicleId;
    private LocalDateTime logDate;
    private BigDecimal fuelConsumed;
    private BigDecimal energyConsumed;
    private BigDecimal mileage;
    private String notes;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public LocalDateTime getLogDate() {
		return logDate;
	}
	public void setLogDate(LocalDateTime logDate) {
		this.logDate = logDate;
	}
	public BigDecimal getFuelConsumed() {
		return fuelConsumed;
	}
	public void setFuelConsumed(BigDecimal fuelConsumed) {
		this.fuelConsumed = fuelConsumed;
	}
	public BigDecimal getEnergyConsumed() {
		return energyConsumed;
	}
	public void setEnergyConsumed(BigDecimal energyConsumed) {
		this.energyConsumed = energyConsumed;
	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

   
}

