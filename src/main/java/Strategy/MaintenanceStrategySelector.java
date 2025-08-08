package Strategy;

public class MaintenanceStrategySelector {
    public static MaintenanceStrategy getStrategy(String type) {
if (type == null || type.trim().isEmpty()) {
    throw new IllegalArgumentException("Maintenance strategy type cannot be null or empty.");
}

switch(type.toLowerCase()){
    case "interval":
        return new IntervalBasedMaintenanceStrategy();
    case "fuel":
        return new FuelUsageMaintenanceStrategy();
    case "mileage":
        return new MileageBasedMaintenanceStrategy();
    default:
        throw new IllegalArgumentException("Unknown maintenance strategy type: " + type);
}
}
}
