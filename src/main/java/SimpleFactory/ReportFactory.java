package SimpleFactory;

import DaoImpl.VehicleMaintenanceHistoryDAOImpl;
import DaoImpl.FuelEnergyLogImp;
public class ReportFactory {

    public static Report createReport(String type) {
        switch (type.toLowerCase()) {
            case "fuel":
                return new FuelUsageReport(new FuelEnergyLogImp());
            case "maintenance":
                return new MaintenanceSummaryReport(new VehicleMaintenanceHistoryDAOImpl());
            default:
                throw new IllegalArgumentException("Unknown report type: " + type);
        }
    }
}

