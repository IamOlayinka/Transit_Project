package Serverlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DTOs.UserDTO;
import DaoImpl.FuelEnergyLogImp;
import model.Vehicle;
import DaoImpl.VehicleDaoImp;

@WebServlet("/ViewFuelEnergyLogsServlet")
public class ViewFuelEnergyLogsServlet extends HttpServlet {

    FuelEnergyLogImp fuelEnergyLogDao = new FuelEnergyLogImp();
    VehicleDaoImp vehicleDao = new VehicleDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<FuelEnergyLog> allLogs = fuelEnergyLogDao.getAllLogs();
        List<FuelEnergyLog> filteredLogs = new java.util.ArrayList<>();

        if ("Manager".equalsIgnoreCase(user.getUserType())) {
            // Manager sees all logs
            filteredLogs = allLogs;
        } else if ("Operator".equalsIgnoreCase(user.getUserType())) {
            int userId = user.getId(); // Make sure UserDTO has getId()

            for (FuelEnergyLog log : allLogs) {
                Vehicle vehicle = vehicleDao.getVehicleById(log.getVehicleId());
                if (vehicle != null && vehicle.getAssignedUserID() == userId) {
                    filteredLogs.add(log);  // Add log if vehicle is assigned to current user
                }
            }
        }

        req.setAttribute("logs", filteredLogs);
        req.getRequestDispatcher("viewFuelEnergyLogs.jsp").forward(req, resp);
    }
}
