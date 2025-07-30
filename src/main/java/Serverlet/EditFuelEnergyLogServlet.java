package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoImpl.FuelEnergyLogImp;
import DTOs.FuelEnergyLog;

@WebServlet("/EditFuelEnergyLogServlet")
public class EditFuelEnergyLogServlet extends HttpServlet {

    private FuelEnergyLogImp fuelEnergyLogDAO = new FuelEnergyLogImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int logId = Integer.parseInt(req.getParameter("id"));

            FuelEnergyLog log = fuelEnergyLogDAO.getLogById(logId); // This method must exist
            if (log != null) {
                req.setAttribute("log", log);
                req.getRequestDispatcher("editFuelEnergyLog.jsp").forward(req, resp);
            } else {
                resp.getWriter().write("Log not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
