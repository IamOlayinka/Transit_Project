package Serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DaoImpl.VehicleDaoImp;
import model.Vehicle;

/**
 * Servlet implementation class MyVehicles
 */
@WebServlet("/MyVehicles")
public class MyVehicles extends HttpServlet {
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        VehicleDaoImp dao = new VehicleDaoImp();
	        List<Vehicle> vehicles = dao.getAllVehicles();
            
	       
	        req.setAttribute("vehicles", vehicles);
	        req.getRequestDispatcher("myVehicles.jsp").forward(req, res);
	    }
	


}
