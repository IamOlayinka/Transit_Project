package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.FuelEnergyLogImp;

/**
 * Servlet implementation class DeleteFuelEnergyLogServlet
 */
@WebServlet("/DeleteFuelEnergyLogServlet")
public class DeleteFuelEnergyLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFuelEnergyLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private FuelEnergyLogImp fuelEnergyLogDAO = new FuelEnergyLogImp();

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int logId = Integer.parseInt(req.getParameter("id"));
            boolean deleted = fuelEnergyLogDAO.deleteFuelEnergyLog(logId);
            if (deleted) {
                resp.sendRedirect("ViewFuelEnergyLogsServlet");
            } else {
                resp.getWriter().write("Failed to delete log.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }

}
