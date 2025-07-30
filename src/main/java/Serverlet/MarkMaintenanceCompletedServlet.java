package Serverlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DTOs.MaintenanceSchedule;
import DTOs.VehicleMaintenanceHistory;
import Dao.FuelEnergyLogDAO;
import Dao.VehicleMaintenanceHistoryDAO;
import DaoImpl.FuelEnergyLogImp;
import DaoImpl.MaintenanceScheduleDAOImpl;
import DaoImpl.VehicleMaintenanceHistoryDAOImpl;

/**
 * Servlet implementation class MarkMaintenanceCompletedServlet
 */
@WebServlet("/MarkMaintenanceCompletedServlet")
public class MarkMaintenanceCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final MaintenanceScheduleDAOImpl scheduleDAO = new MaintenanceScheduleDAOImpl();
    private final FuelEnergyLogDAO logDAO = new FuelEnergyLogImp();
    private final VehicleMaintenanceHistoryDAO historyDAO = new VehicleMaintenanceHistoryDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        // Step 1: Update maintenance status
        scheduleDAO.updateStatus(id, "Completed");

        // Step 2: Get the completed schedule
        MaintenanceSchedule schedule = scheduleDAO.getById(id);

        // Step 3: Get latest mileage from FuelEnergyLog
        FuelEnergyLog latestLog = logDAO.getLatestLogByVehicle(schedule.getVehicleId());
        Double mileageAtService = (latestLog != null && latestLog.getMileage() != null)
                ? latestLog.getMileage().doubleValue() : null;

        // Step 4: Save into maintenance history
        VehicleMaintenanceHistory record = new VehicleMaintenanceHistory();
        record.setVehicleId(schedule.getVehicleId());
        record.setMaintenanceDate(LocalDateTime.now());
        record.setMileageAtService(mileageAtService);
        record.setNotes("Completed: " + schedule.getRecommendation());

        historyDAO.addMaintenanceRecord(record);

        // Redirect
        resp.sendRedirect("ViewMaintenanceScheduleServlet");
    }
}
