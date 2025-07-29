package model;

public class Vehicle {
    private int id;
    private String vehicleNumber;
    private String vehicleType;
    private String fuelType;
    private double consumptionRate;
    private int maxPassengers;
    private String assignedRoute;

    public Vehicle(int id, String vehicleNumber, String vehicleType, String fuelType,
                   double consumptionRate, int maxPassengers, String assignedRoute) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.consumptionRate = consumptionRate;
        this.maxPassengers = maxPassengers;
        this.assignedRoute = assignedRoute;
    }

    public int getId() { return id; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getVehicleType() { return vehicleType; }
    public String getFuelType() { return fuelType; }
    public double getConsumptionRate() { return consumptionRate; }
    public int getMaxPassengers() { return maxPassengers; }
    public String getAssignedRoute() { return assignedRoute; }
}
