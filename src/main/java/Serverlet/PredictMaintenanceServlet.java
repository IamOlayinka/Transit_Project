package Serverlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.FuelEnergyLog;
import DaoImpl.FuelEnergyLogImp;
import Strategy.MaintenanceStrategy;
import Strategy.MaintenanceStrategySelector;

/**
 * Servlet implementation class PredictMaintenanceServlet
 */
@WebServlet("/PredictMaintenanceServlet")
public class PredictMaintenanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final FuelEnergyLogImp logDAO = new FuelEnergyLogImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strategyType = req.getParameter("strategy");
        System.out.println("Submitted strategy: " + strategyType);
        System.out.println("Request URI: " + req.getRequestURI());
        System.out.println("Query String: " + req.getQueryString());
        System.out.println("strategyType = " + strategyType);

// e.g. "mileage" or "fuel"
        
        if (strategyType == null || strategyType.trim().isEmpty()) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid strategy type");
        	return;
        }
        
        MaintenanceStrategy strategy = MaintenanceStrategySelector.getStrategy(strategyType);
        
        if (strategy == null) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported Strategy Type");
        	return;
        }

        List<FuelEnergyLog> logs = logDAO.getAllLogs();
        Map<FuelEnergyLog, String> predictions = new LinkedHashMap<>();

        for (FuelEnergyLog log : logs) {
            predictions.put(log, strategy.predictMaintenance(log));
        }

        req.setAttribute("predictions", predictions);
        req.setAttribute("strategyUsed", strategyType);
        req.getRequestDispatcher("predictMaintenance.jsp").forward(req, resp);
    }

}
