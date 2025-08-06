package Serverlet;

import DaoImpl.VehicleDaoImp;
import Validator.vehicleValidator;
import model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;


@WebServlet("/registerVehicle")
public class RegisterVehicle extends HttpServlet{
	

	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	          throws ServletException, IOException {

	        String vehicleNumber = req.getParameter("vehicleNumber");
	        String vehicleType = req.getParameter("vehicleType");
	        String fuelType = req.getParameter("fuelType");
	        double consumptionRate = Double.parseDouble(req.getParameter("consumptionRate"));
	        int maxPassengers = Integer.parseInt(req.getParameter("maxPassengers"));
	        String assignedRoute = req.getParameter("assignedRoute");
	        int assignedUserID = Integer.parseInt(req.getParameter("assignedUserID"));
	        
	        Map<String, String> errors = vehicleValidator.validate(req);

	        if (!errors.isEmpty()) {
	            req.getSession().setAttribute("message", String.join(" ", errors.values()));
	            res.sendRedirect("vehicle.jsp");
	            return;
	        }

	        double consumption = Double.parseDouble(req.getParameter("consumptionRate"));
	        int passengers = Integer.parseInt(req.getParameter("maxPassengers"));
	        
	        Vehicle vehicle = new Vehicle(
	        	    0, // ID will be auto-generated
	        	    vehicleNumber,
	        	    vehicleType,
	        	    fuelType,
	        	    consumption,
	        	    passengers,
	        	    assignedRoute,
	        	    assignedUserID
	        	);

	        VehicleDaoImp dao = new VehicleDaoImp();
	        boolean success = dao.registerVehicle(vehicle);

	        if (success) {
	        	HttpSession session = req.getSession();
	        	if (success) {
	        	    session.setAttribute("message", "Vehicle registered successfully.");
	        	} else {
	        	    session.setAttribute("message", "Vehicle registration failed.");
	        	}
	        	res.sendRedirect("registerVehicle.jsp");
	        } else {
	            res.sendRedirect("registerVehicle.jsp?error=1");
	        }
	    }
	}

