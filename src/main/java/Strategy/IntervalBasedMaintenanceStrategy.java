package Strategy;

import DTOs.FuelEnergyLog;
import DTOs.VehicleMaintenanceHistory;
import Dao.VehicleMaintenanceHistoryDAO;
import DaoImpl.VehicleMaintenanceHistoryDAOImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class IntervalBasedMaintenanceStrategy implements MaintenanceStrategy {

    private final VehicleMaintenanceHistoryDAO historyDAO = new VehicleMaintenanceHistoryDAOImpl();

    @Override
    public String predictMaintenance(FuelEnergyLog log) {
        VehicleMaintenanceHistory last = historyDAO.getLastMaintenanceByVehicle(log.getVehicleId());

        if (last == null) {
            return "No history found — schedule baseline maintenance";
        }

        boolean dueByMileage = false;
        boolean dueByTime = false;

        // Mileage check
        if (log.getMileage() != null && last.getMileageAtService() != null) {
        	double mileageDiff = log.getMileage().doubleValue() - last.getMileageAtService();
            dueByMileage = mileageDiff >= 50000;
        }

        // Time check (6 months = ~180 days)
        if (last.getMaintenanceDate() != null) {
            long monthsSince = ChronoUnit.MONTHS.between(last.getMaintenanceDate(), LocalDateTime.now());
            dueByTime = monthsSince >= 6;
        }

        if (dueByMileage && dueByTime) {
            return "Due: Major service (6 months & 40,000km)";
        } else if (dueByMileage) {
            return "Due: Mileage-based maintenance (40,000km)";
        } else if (dueByTime) {
            return "Due: Time-based maintenance (6 months)";
        }

        return "No maintenance needed yet";
    }
}
