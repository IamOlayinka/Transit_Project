package Serverlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.BreakLog;
import DaoImpl.BreakLogImp;

@WebServlet("/AddBreakLogServlet")
public class AddBreakLog extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the form page
        request.getRequestDispatcher("breakLogForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BreakLogImp breakLogDAO = new BreakLogImp();  

        try {
            int operatorId = Integer.parseInt(request.getParameter("operatorId"));
            String breakType = request.getParameter("breakType");
            
            String startTimeStr = request.getParameter("startTime");
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            
            String durationStr = request.getParameter("duration");
            int duration = Integer.parseInt(durationStr); // duration in minutes

            String vehicleIdStr = request.getParameter("vehicleId");
            Integer vehicleId = (vehicleIdStr == null || vehicleIdStr.isEmpty()) ? null : Integer.parseInt(vehicleIdStr);
            
            String notes = request.getParameter("notes");

            BreakLog breakLog = new BreakLog();
            breakLog.setOperatorId(operatorId);
            breakLog.setBreakType(breakType);
            breakLog.setBreakStartTime(startTime);
            breakLog.setDuration(duration);
            breakLog.setBreakEndTime(startTime.plusMinutes(duration)); // Calculate end time here
            breakLog.setVehicleId(vehicleId);
            breakLog.setNotes(notes);

            boolean success = breakLogDAO.addBreakLog(breakLog);
            if (success) {
                response.sendRedirect("BreakLogList");
            } else {
                request.setAttribute("error", "Failed to start break");
                request.getRequestDispatcher("breakLogForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("breakLogForm.jsp").forward(request, response);
        }
    }
}
