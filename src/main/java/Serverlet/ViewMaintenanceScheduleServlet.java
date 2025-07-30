package Serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.MaintenanceSchedule;
import DaoImpl.MaintenanceScheduleDAOImpl;

/**
 * Servlet implementation class ViewMaintenanceScheduleServlet
 */
@WebServlet("/ViewMaintenanceScheduleServlet")
public class ViewMaintenanceScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private final MaintenanceScheduleDAOImpl scheduleDAO = new MaintenanceScheduleDAOImpl();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        List<MaintenanceSchedule> schedules = scheduleDAO.getUpcomingSchedules();
	        req.setAttribute("schedules", schedules);
	        req.getRequestDispatcher("viewMaintenanceSchedule.jsp").forward(req, resp);
	    }
}
