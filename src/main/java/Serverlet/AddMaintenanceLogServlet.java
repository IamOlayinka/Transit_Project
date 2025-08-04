package Serverlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.MaintenanceScheduleDAOImpl;
import DTOs.MaintenanceSchedule;

@WebServlet("/AddMaintenanceLogServlet")
public class AddMaintenanceLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));
            String dateStr = req.getParameter("predictedDate");
            String strategy = req.getParameter("strategy");
            String recommendation = req.getParameter("recommendation");
            String status = req.getParameter("status");

            // Parse predicted date
            Date predictedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);



            // Populate model
            MaintenanceSchedule schedule = new MaintenanceSchedule();
            
            LocalDateTime ldt = predictedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            schedule.setVehicleId(vehicleId);
            schedule.setPredictedDate(ldt);
            schedule.setStrategyUsed(strategy);
            schedule.setRecommendation(recommendation);
            schedule.setStatus(status);

            // Save using DAO
            MaintenanceScheduleDAOImpl dao = new MaintenanceScheduleDAOImpl();
            dao.saveSchedule(schedule);

            // Redirect or display confirmation
            resp.sendRedirect("ViewMaintenanceScheduleServlet");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data or database error.");
        }
    }
}
