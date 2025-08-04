package Serverlet;

import Dao.VehicleDao;
import Dao.GpsLogDao;
import DTOs.UserDTO;
import model.Vehicle;
import DaoImpl.GpsLogImp;
import DaoImpl.VehicleDaoImp;
import Builder.GpsLog;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/gpsLog")
public class GpsLogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // Get list of all vehicles (later: filter by operator-assigned if needed)
        VehicleDaoImp dao = new VehicleDaoImp();
        List<Vehicle> vehicles = dao.getAllVehicles();
        
        GpsLogImp gpsDao = new GpsLogImp();
        List<GpsLog> logs = gpsDao.getAllLogs();

        System.out.println("Getting vehicles...");
        System.out.println("User in session: " + user);
        System.out.println("Vehicle count: " + (vehicles != null ? vehicles.size() : "null"));

        req.setAttribute("vehicles", vehicles);
        req.setAttribute("logs", logs);
        req.getRequestDispatcher("gpslog.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null || !"Operator".equalsIgnoreCase(user.getUserType())) {
            res.sendRedirect("login.jsp");
            return;
        }

        int vehicleId = Integer.parseInt(req.getParameter("vehicleId"));
        String stationName = req.getParameter("stationName");
        LocalDateTime arrival = LocalDateTime.parse(req.getParameter("arrivalTime"));
        LocalDateTime departure = LocalDateTime.parse(req.getParameter("departureTime"));

        GpsLog log = new GpsLog(0, vehicleId, stationName, arrival, departure, user.getId());

        boolean success = new GpsLogImp().saveGpsLog(log);

        session.setAttribute("message", success
                ? "GPS log saved successfully."
                : "Failed to save GPS log.");

        res.sendRedirect("gpsLog");
    }
}
