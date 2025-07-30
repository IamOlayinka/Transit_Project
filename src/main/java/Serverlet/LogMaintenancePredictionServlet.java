package Serverlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DTOs.MaintenanceSchedule;
import Dao.FuelEnergyLogDAO;
import DaoImpl.FuelEnergyLogImp;
import DaoImpl.MaintenanceScheduleDAOImpl;
import Strategy.MaintenanceStrategy;
import Strategy.MaintenanceStrategySelector;

/**
 * Servlet implementation class LogMaintenancePredictionServlet
 */
@WebServlet("/LogMaintenancePredictionServlet")
public class LogMaintenancePredictionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final FuelEnergyLogImp logDAO = new FuelEnergyLogImp();
    private final MaintenanceScheduleDAOImpl scheduleDAO = new MaintenanceScheduleDAOImpl();
       
 
    public LogMaintenancePredictionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strategyType = req.getParameter("strategy");
        MaintenanceStrategy strategy = MaintenanceStrategySelector.getStrategy(strategyType);

        List<FuelEnergyLog> logs = logDAO.getAllLogs();

        for (FuelEnergyLog log : logs) {
            String recommendation = strategy.predictMaintenance(log);
            if (!recommendation.equals("No maintenance needed")) {
                MaintenanceSchedule schedule = new MaintenanceSchedule();
                schedule.setVehicleId(log.getVehicleId());
                schedule.setPredictedDate(LocalDateTime.now().plusDays(7)); // Example: next week
                schedule.setRecommendation(recommendation);
                schedule.setStrategyUsed(strategyType);
                scheduleDAO.saveSchedule(schedule);
            }
        }

        resp.sendRedirect("ViewMaintenanceScheduleServlet");
    }
}


