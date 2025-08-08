package Serverlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.VehicleDaoImp;
import Validator.vehicleValidator;
import model.Vehicle;

/**
 * Servlet implementation class EditVehicle
 */


@WebServlet("/editVehicle")
public class EditVehicle extends HttpServlet {
//    protected void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//
//    	Map<String, String> errors = vehicleValidator.validate(req);
//
//        if (!errors.isEmpty()) {
//            req.getSession().setAttribute("message", String.join(" ", errors.values()));
//            res.sendRedirect("editVehicle?id=" + req.getParameter("id"));
//            return;
//        }
//        
//        
//        int id = Integer.parseInt(req.getParameter("id"));
//        VehicleDaoImp dao = new VehicleDaoImp();
//        Vehicle vehicle = dao.getVehicleById(id);
//        
//        
//
//        req.setAttribute("vehicle", vehicle);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("editVehicle.jsp");
//        dispatcher.forward(req, res);
//    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        VehicleDaoImp dao = new VehicleDaoImp();
        Vehicle vehicle = dao.getVehicleById(id);

        // Perform validation AFTER fetching vehicle (if necessary)
        Map<String, String> errors = vehicleValidator.validate(req);

        req.setAttribute("vehicle", vehicle);

        if (!errors.isEmpty()) {
            req.setAttribute("validationErrors", errors);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("editVehicle.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String number = req.getParameter("vehicleNumber");
        String type = req.getParameter("vehicleType");
        String fuel = req.getParameter("fuelType");
        double consumption = Double.parseDouble(req.getParameter("consumptionRate"));
        int passengers = Integer.parseInt(req.getParameter("maxPassengers"));
        String route = req.getParameter("assignedRoute");
        int userID = Integer.parseInt(req.getParameter("assignedUserID"));

        Vehicle vehicle = new Vehicle(
            id, number, type, fuel, consumption, passengers, route, userID
        );

        VehicleDaoImp dao = new VehicleDaoImp();
        dao.updateVehicle(vehicle);

        res.sendRedirect("VehicleList");
    }
}
