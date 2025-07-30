package Serverlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DaoImpl.FuelEnergyLogImp;

/**
 * Servlet implementation class AddFuelEnergyLogServlet
 */
@WebServlet("/AddFuelEnergyLogServlet")
public class AddFuelEnergyLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFuelEnergyLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		        FuelEnergyLogImp fuelEnergyLog = new FuelEnergyLogImp();  

	        try {
	            int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));
	            String logDateStr = req.getParameter("logDate");
	            LocalDateTime logDate = LocalDateTime.parse(logDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

	            String fuelConsumedStr = req.getParameter("fuelConsumed");
	            BigDecimal fuelConsumed = (fuelConsumedStr == null || fuelConsumedStr.isEmpty()) ? null : new BigDecimal(fuelConsumedStr);

	            String energyConsumedStr = req.getParameter("energyConsumed");
	            BigDecimal energyConsumed = (energyConsumedStr == null || energyConsumedStr.isEmpty()) ? null : new BigDecimal(energyConsumedStr);

	            String mileageStr = req.getParameter("mileage");
	            BigDecimal mileage = (mileageStr == null || mileageStr.isEmpty()) ? null : new BigDecimal(mileageStr);

	            String notes = req.getParameter("notes");

	            FuelEnergyLog log = new FuelEnergyLog();
	            log.setVehicleId(vehicleId);
	            log.setLogDate(logDate);
	            log.setFuelConsumed(fuelConsumed);
	            log.setEnergyConsumed(energyConsumed);
	            log.setMileage(mileage);
	            log.setNotes(notes);

	            boolean success = fuelEnergyLog.addFuelEnergyLog(log);
	            if (success) {
	                resp.sendRedirect("ViewFuelEnergyLogsServlet");
	            } else {
	                resp.getWriter().write("Failed to add log.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.getWriter().write("Error: " + e.getMessage());
	        }
	}

}
