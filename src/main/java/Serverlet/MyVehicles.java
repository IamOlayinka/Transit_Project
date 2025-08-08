package Serverlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTOs.UserDTO;
import DaoImpl.VehicleDaoImp;
import model.Vehicle;

@WebServlet("/MyVehicles")
public class MyVehicles extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // Get logged-in user
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        
        VehicleDaoImp dao = new VehicleDaoImp();
        
        // Get only vehicles assigned to this user - more efficient
        List<Vehicle> vehicles = dao.getVehiclesByAssignedUserID(user.getId());
        
        req.setAttribute("vehicles", vehicles);
        req.getRequestDispatcher("myVehicles.jsp").forward(req, res);
    }
}