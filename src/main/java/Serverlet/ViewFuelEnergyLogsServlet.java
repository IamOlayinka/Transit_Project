package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DaoImpl.FuelEnergyLogImp;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewFuelEnergyLogsServlet")
public class ViewFuelEnergyLogsServlet extends HttpServlet {

	FuelEnergyLogImp fuelEnergyLog = new FuelEnergyLogImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FuelEnergyLog> logs = fuelEnergyLog.getAllLogs();  // You may want to implement this method
        req.setAttribute("logs", logs);
        req.getRequestDispatcher("viewFuelEnergyLogs.jsp").forward(req, resp);
    }
}