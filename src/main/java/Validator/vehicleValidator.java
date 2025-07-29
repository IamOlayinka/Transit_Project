package Validator;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class vehicleValidator {

	    public static Map<String, String> validate(HttpServletRequest req) {
	        Map<String, String> errors = new HashMap<>();

	        String number = req.getParameter("vehicleNumber");
	        String type = req.getParameter("vehicleType");
	        String fuel = req.getParameter("fuelType");
	        String consumptionStr = req.getParameter("consumptionRate");
	        String passengersStr = req.getParameter("maxPassengers");
	        String route = req.getParameter("assignedRoute");

	        if (isBlank(number)) errors.put("vehicleNumber", "Vehicle number is required.");
	        if (isBlank(type)) errors.put("vehicleType", "Vehicle type is required.");
	        if (isBlank(fuel)) errors.put("fuelType", "Fuel type is required.");
	        if (isBlank(consumptionStr)) errors.put("consumptionRate", "Consumption rate is required.");
	        if (isBlank(passengersStr)) errors.put("maxPassengers", "Max passengers is required.");
	        if (isBlank(route)) errors.put("assignedRoute", "Assigned route is required.");

	        try {
	            if (!isBlank(consumptionStr)) Double.parseDouble(consumptionStr);
	        } catch (NumberFormatException e) {
	            errors.put("consumptionRate", "Consumption rate must be a number.");
	        }

	        try {
	            if (!isBlank(passengersStr)) Integer.parseInt(passengersStr);
	        } catch (NumberFormatException e) {
	            errors.put("maxPassengers", "Max passengers must be a number.");
	        }

	        return errors;
	    }

	    private static boolean isBlank(String s) {
	        return s == null || s.trim().isEmpty();
	    }
	}


