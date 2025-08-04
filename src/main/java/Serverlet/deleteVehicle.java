package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DaoImpl.VehicleDaoImp;
import DTOs.UserDTO;

@WebServlet("/deleteVehicle")
public class deleteVehicle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (!"Manager".equalsIgnoreCase(user.getUserType())) {
            res.sendRedirect("vehicleList?error=unauthorized");
            return;
        }
        int id = 0;
        
        
        try {
            id = Integer.parseInt(req.getParameter("id").trim());
        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid vehicle ID.");
            res.sendRedirect("VehicleList");
            return;
        }

        VehicleDaoImp dao = new VehicleDaoImp();
        boolean deleted = dao.deleteVehicle(id);

        if (deleted) {
            session.setAttribute("message", "Vehicle deleted successfully.");
        } else {
            session.setAttribute("message", "Failed to delete vehicle.");
        }

        res.sendRedirect("VehicleList");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Optional: Forward GET to POST or disallow GET requests to delete
        res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Use POST to delete vehicles.");
    }
}
