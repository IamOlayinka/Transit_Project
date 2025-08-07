package Serverlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Builder.GpsLog;
import DTOs.UserDTO;
import DaoImpl.GpsLogImp;
import DaoImpl.VehicleDaoImp;
import model.Vehicle;

/**
 * Servlet implementation class AddGpsLogServlet
 */
@WebServlet("/AddGpsLogServlet")
public class AddGpsLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGpsLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    HttpSession session = req.getSession(false);
	    UserDTO user = (UserDTO) session.getAttribute("user");

	    if (user == null) {
	        res.sendRedirect("login.jsp");
	        return;
	    }

	    VehicleDaoImp dao = new VehicleDaoImp();
	    List<Vehicle> vehicles = dao.getAllVehicles();
	    GpsLogImp gpsDao = new GpsLogImp();
	    List<GpsLog> logs = gpsDao.getAllLogs();

	
	    req.setAttribute("vehicles", vehicles != null ? vehicles : new ArrayList<>());
	    req.setAttribute("logs", logs != null ? logs : new ArrayList<>());
	    req.getRequestDispatcher("gpslog.jsp").forward(req, res);
	}


}
