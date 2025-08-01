package SimpleFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import DTOs.VehicleMaintenanceHistory;
import Dao.VehicleMaintenanceHistoryDAO;

public class MaintenanceSummaryReport implements Report {

    private final VehicleMaintenanceHistoryDAO historyDAO;

    public MaintenanceSummaryReport(VehicleMaintenanceHistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maintenance Summary Report:\n");

        Map<Integer, List<VehicleMaintenanceHistory>> grouped = historyDAO.getHistoryGroupedByVehicle();

        for (Map.Entry<Integer, List<VehicleMaintenanceHistory>> entry : grouped.entrySet()) {
            int vehicleId = entry.getKey();
            List<VehicleMaintenanceHistory> records = entry.getValue();

            sb.append("Vehicle ID: ").append(vehicleId).append("\n");
            sb.append("  Maintenance Count: ").append(records.size()).append("\n");

            Optional<VehicleMaintenanceHistory> last = records.stream()
                    .max(Comparator.comparing(VehicleMaintenanceHistory::getMaintenanceDate));
            if (last.isPresent()) {
                sb.append("  Last Maintained: ").append(last.get().getMaintenanceDate()).append("\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

