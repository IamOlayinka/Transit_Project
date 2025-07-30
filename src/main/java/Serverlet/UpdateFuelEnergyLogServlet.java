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
 * Servlet implementation class UpdateFuelEnergyLogServlet
 */
@WebServlet("/UpdateFuelEnergyLogServlet")
public class UpdateFuelEnergyLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FuelEnergyLogImp fuelEnergyLogDAO = new FuelEnergyLogImp();
       
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
            int id = Integer.parseInt(req.getParameter("id"));
            int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));

            String logDateStr = req.getParameter("logDate");
            LocalDateTime logDate = LocalDateTime.parse(logDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String fuelStr = req.getParameter("fuelConsumed");
            BigDecimal fuel = (fuelStr == null || fuelStr.isEmpty()) ? null : new BigDecimal(fuelStr);

            String energyStr = req.getParameter("energyConsumed");
            BigDecimal energy = (energyStr == null || energyStr.isEmpty()) ? null : new BigDecimal(energyStr);

            String mileageStr = req.getParameter("mileage");
            BigDecimal mileage = (mileageStr == null || mileageStr.isEmpty()) ? null : new BigDecimal(mileageStr);

            String notes = req.getParameter("notes");

            FuelEnergyLog log = new FuelEnergyLog();
            log.setId(id);
            log.setVehicleId(vehicleId);
            log.setLogDate(logDate);
            log.setFuelConsumed(fuel);
            log.setEnergyConsumed(energy);
            log.setMileage(mileage);
            log.setNotes(notes);

            boolean success = fuelEnergyLogDAO.updateFuelEnergyLog(log);
            if (success) {
                resp.sendRedirect("ViewFuelEnergyLogsServlet");
            } else {
                resp.getWriter().write("Failed to update log.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
	}

}
