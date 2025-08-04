package Strategy;

public class MaintenanceStrategySelector {
    public static MaintenanceStrategy getStrategy(String type) {
    	
    	 if (type == null || type.trim().isEmpty()) {
             // Throw an exception because 'type' is a mandatory parameter
             throw new IllegalArgumentException("Maintenance strategy type cannot be null or empty.");
         }
    	
    	switch(type){
    	case "interval":
    	    return new IntervalBasedMaintenanceStrategy();
    	case "fuel":
    		return new FuelUsageMaintenanceStrategy();
    	default: 
    		throw new IllegalArgumentException("Unknown maintenance strategy type: " + type);
    	}
}
}
