package Strategy;

public class MaintenanceStrategySelector {
    public static MaintenanceStrategy getStrategy(String type) {
    	switch(type){
    	case "interval":
    	    return new IntervalBasedMaintenanceStrategy();
    	case "fuel":
    		return new FuelUsageMaintenanceStrategy();
    	default: 
    		 return new MileageBasedMaintenanceStrategy(); 
    	}
}
}
