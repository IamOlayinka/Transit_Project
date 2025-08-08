package Serverlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.BreakLogImp;
import DTOs.BreakLog;
import DTOs.UserDTO;

@WebServlet("/BreakLogList")
public class BreakLogList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Handle deletion if this is a POST request
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null || !"Manager".equalsIgnoreCase(user.getUserType())) {
            req.getSession().setAttribute("message", "Unauthorized: Only Managers can delete break logs.");
            res.sendRedirect("BreakLogList");
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            BreakLogImp dao = new BreakLogImp();
            boolean success = dao.deleteBreakLog(id);
            
            if (success) {
                req.getSession().setAttribute("message", "Break log deleted successfully");
            } else {
                req.getSession().setAttribute("message", "Failed to delete break log");
            }
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("message", "Invalid break log ID");
        }
        
        res.sendRedirect("BreakLogList");
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Check user role - only managers should see all break logs
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        BreakLogImp dao = new BreakLogImp();
        List<BreakLog> breakLogs;
        
        if ("Manager".equalsIgnoreCase(user.getUserType())) {
            // Manager can see all break logs
            breakLogs = dao.getAllBreakLogs();
        } else {
            // Operators can only see their own break logs
            breakLogs = dao.getBreakLogsByOperator(user.getId());
        }
        
        
        for (BreakLog log : breakLogs) {
            LocalDateTime start = log.getBreakStartTime();
            LocalDateTime end = log.getBreakEndTime();

            long breakMinutes;
            if (end == null && log.getBreakType().equals("break")) {
                breakMinutes = 30;
                log.setBreakEndTime(start.plusMinutes(30)); 
                dao.updateBreakLog(log);
            } else {
                breakMinutes = Duration.between(start, end).toMinutes();
                if (breakMinutes > 30 && log.getBreakType().equals("break")) {
                    breakMinutes = 30;
                    log.setBreakEndTime(start.plusMinutes(30)); 
                    dao.updateBreakLog(log);
                }
            }
            System.out.println(log.getBreakEndTime());
        }
        
        req.setAttribute("breakLogs", breakLogs);
        req.getRequestDispatcher("breakLogList.jsp").forward(req, res);
        System.out.println("Total Break Logs Found: " + breakLogs.size()+"!!!!!!!!!!!!!!");
    }
}