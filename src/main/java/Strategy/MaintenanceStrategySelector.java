package Strategy;

public class MaintenanceStrategySelector {
    public static MaintenanceStrategy getStrategy(String type) {
    	if (type == null) throw new IllegalArgumentException("Type cannot be null");
    	
    	switch(type.toLowerCase()){
    	case "interval":
    	    return new IntervalBasedMaintenanceStrategy();
    	case "fuel":
    		return new FuelUsageMaintenanceStrategy();
    	
    	case "mileae":
    		return new MileageBasedMaintenanceStrategy();
    	default:
    		  throw new IllegalArgumentException("Unknown strategy type: " + type);
    	}
}
}
