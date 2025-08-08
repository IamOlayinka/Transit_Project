package Serverlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.BreakLog;
import DTOs.UserDTO;
import DaoImpl.BreakLogImp;
import DaoImpl.FuelEnergyLogImp;
import DaoImpl.VehicleDaoImp;
import model.Vehicle;

@WebServlet("/OperatorDashboardServlet")
public class OperatorDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final VehicleDaoImp vehicleDAO = new VehicleDaoImp();
    private final FuelEnergyLogImp logDAO = new FuelEnergyLogImp();
    private final BreakLogImp breakLogDAO = new BreakLogImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        int userID = user.getId();

        // Stats - Now using the correct DAO method
        req.setAttribute("vehicleCount", vehicleDAO.countVehiclesByUserID(userID));
        req.setAttribute("fuelLogCount", logDAO.countLogsByUserID(userID));

        // Active break/out_of_service status for current user
        BreakLog activeBreak = breakLogDAO.getActiveBreakByOperator(userID);
        req.setAttribute("activeBreak", activeBreak);

        // Today's breaks
        List<BreakLog> todaysBreaks = breakLogDAO.getBreakLogsByOperatorAndDate(userID, LocalDateTime.now().toLocalDate());
        req.setAttribute("todaysBreaks", todaysBreaks);

        req.getRequestDispatcher("operatorDashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        int userID = user.getId();
        String action = req.getParameter("action");

        try {
            if ("startBreak".equals(action)) {
                BreakLog newBreak = new BreakLog();
                newBreak.setOperatorId(userID);
                newBreak.setBreakStartTime(LocalDateTime.now());
                newBreak.setBreakType("break");
                breakLogDAO.addBreakLog(newBreak);

            } else if ("endBreak".equals(action)) {
                BreakLog activeBreak = breakLogDAO.getActiveBreakByOperator(userID);
                if (activeBreak != null && "break".equals(activeBreak.getBreakType())) {
                    activeBreak.setBreakEndTime(LocalDateTime.now());
                    breakLogDAO.updateBreakLog(activeBreak);
                }

            } else if ("startOutOfService".equals(action)) {
                BreakLog newStatus = new BreakLog();
                newStatus.setOperatorId(userID);
                newStatus.setBreakStartTime(LocalDateTime.now());
                newStatus.setBreakType("out_of_service");
                breakLogDAO.addBreakLog(newStatus);

            } else if ("endOutOfService".equals(action)) {
                BreakLog activeStatus = breakLogDAO.getActiveBreakByOperator(userID);
                if (activeStatus != null && "out_of_service".equals(activeStatus.getBreakType())) {
                    activeStatus.setBreakEndTime(LocalDateTime.now());
                    breakLogDAO.updateBreakLog(activeStatus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, set an error message attribute here
        }

        // Redirect to GET to reload data and refresh page
        resp.sendRedirect("OperatorDashboardServlet");
    }
}