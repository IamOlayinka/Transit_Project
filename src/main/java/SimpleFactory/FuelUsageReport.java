package SimpleFactory;

import Dao.FuelEnergyLogDAO;

import java.util.List;
import java.util.Map;

import DTOs.FuelEnergyLog;

public class FuelUsageReport implements Report {

    private final FuelEnergyLogDAO logDAO;

    public FuelUsageReport(FuelEnergyLogDAO logDAO) {
        this.logDAO = logDAO;
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fuel Usage Report:\n");

        Map<Integer, List<FuelEnergyLog>> grouped = logDAO.getLogsGroupedByVehicle();

        for (Map.Entry<Integer, List<FuelEnergyLog>> entry : grouped.entrySet()) {
            int vehicleId = entry.getKey();
            List<FuelEnergyLog> logs = entry.getValue();

            double totalFuel = 0, totalEnergy = 0, totalMileage = 0;
            int count = 0;

            for (FuelEnergyLog log : logs) {
                if (log.getFuelConsumed() != null) totalFuel += log.getFuelConsumed().doubleValue();
                if (log.getEnergyConsumed() != null) totalEnergy += log.getEnergyConsumed().doubleValue();
                if (log.getMileage() != null) totalMileage += log.getMileage().doubleValue();
                count++;
            }

            sb.append("Vehicle ID: ").append(vehicleId).append("\n");
            sb.append("  Total Fuel: ").append(totalFuel).append(" L\n");
            sb.append("  Total Energy: ").append(totalEnergy).append(" kWh\n");
            sb.append("  Avg Mileage: ").append(count > 0 ? (totalMileage / count) : 0).append(" km\n\n");
        }

        return sb.toString();
    }
}
