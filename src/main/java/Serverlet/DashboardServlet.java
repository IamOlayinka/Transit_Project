package Serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.MaintenanceSchedule;
import DaoImpl.FuelEnergyLogImp;
import DaoImpl.MaintenanceScheduleDAOImpl;
import DaoImpl.VehicleDaoImp;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final VehicleDaoImp vehicleDAO = new VehicleDaoImp();
     private final FuelEnergyLogImp logDAO = new FuelEnergyLogImp();
     private final MaintenanceScheduleDAOImpl maintenanceDAO = new MaintenanceScheduleDAOImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */

      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setAttribute("vehicleCount", vehicleDAO.countVehicles());
            req.setAttribute("fuelLogCount", logDAO.countLogs());
            req.setAttribute("completedMaintenances", maintenanceDAO.countCompleted());

            List<MaintenanceSchedule> upcoming = maintenanceDAO.getUpcomingWithinDays(7);
            req.setAttribute("upcomingMaintenances", upcoming);

            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        }

}
