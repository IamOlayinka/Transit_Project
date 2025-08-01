package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DaoImpl.VehicleDaoImp;

/**
 * Servlet implementation class deleteVehicle
 */
@WebServlet("/deleteVehicle")
public class deleteVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        DTOs.UserDTO user = (DTOs.UserDTO) session.getAttribute("user");

        // Check if user is a Manager
        if (!"Manager".equalsIgnoreCase(user.getUserType())) {
            res.sendRedirect("vehicleList?error=unauthorized");
            return;
        }

        // Proceed with deletion
        int id = Integer.parseInt(req.getParameter("id"));
        VehicleDaoImp dao = new VehicleDaoImp();
        boolean deleted = dao.deleteVehicle(id);

        if (deleted) {
            session.setAttribute("message", "Vehicle deleted successfully.");
        } else {
            session.setAttribute("message", "Failed to delete vehicle.");
        }

        res.sendRedirect("VehicleList");
    }

}
