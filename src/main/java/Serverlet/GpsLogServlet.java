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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gpsLog")
public class GpsLogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("user");

        
        if (user == null || !"Manager".equalsIgnoreCase(user.getUserType())) {
            if (session != null) {
                session.setAttribute("errorMessage", "Please log in as a Manager to access this feature.");
            }  
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

        res.sendRedirect("AddGpsLogServlet");
    }
}
