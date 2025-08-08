package Serverlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.BreakLogImp;
import DTOs.BreakLog;

@WebServlet("/StopBreakLogServlet")
public class StopBreakLog extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private BreakLogImp breakLogDAO = new BreakLogImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int breakLogId = Integer.parseInt(req.getParameter("id"));
            
            BreakLog breakLog = breakLogDAO.getBreakLogById(breakLogId);
            if (breakLog == null) {
                resp.getWriter().write("Break log not found.");
                return;
            }
            
            LocalDateTime now = LocalDateTime.now();

            if (breakLog.getBreakEndTime().isAfter(now)) {
                breakLog.setBreakEndTime(now);
                System.out.println("HIIIIIII: "+breakLog.getBreakEndTime());

                long durationMinutes = Duration.between(breakLog.getBreakStartTime(), now).toMinutes();
                breakLog.setDuration((int) durationMinutes);
                
                System.out.println("BYEEEEEEE: "+breakLog.getDuration());
                

                boolean stopped = breakLogDAO.updateBreakLog(breakLog);
                if (stopped) {
                	req.getSession().setAttribute("message", "Break log stopped successfully.");
                    resp.sendRedirect("BreakLogList");
                } else {
                    resp.getWriter().write("Failed to update break log.");
                }
            } else {
                resp.getWriter().write("Break already stopped.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
