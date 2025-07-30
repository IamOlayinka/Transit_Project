package Serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DaoImpl.FuelEnergyLogImp;

/**
 * Servlet implementation class ViewFuelEnergyLogsByVehicleServlet
 */
@WebServlet("/ViewFuelEnergyLogsByVehicleServlet")
public class ViewFuelEnergyLogsByVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FuelEnergyLogImp fuelEnergyLogDAO = new FuelEnergyLogImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFuelEnergyLogsByVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
		    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        try {
	            int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));

	            List<FuelEnergyLog> logs = fuelEnergyLogDAO.getLogsByVehicle(vehicleId);
	            req.setAttribute("logs", logs);
	            req.setAttribute("filteredVehicleId", vehicleId); // Optional: show message

	            req.getRequestDispatcher("viewFuelEnergyLogs.jsp").forward(req, resp);
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.getWriter().write("Error: " + e.getMessage());
	        }
	    }

}
