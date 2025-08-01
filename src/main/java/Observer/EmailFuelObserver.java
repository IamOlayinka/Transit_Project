package Observer;

import DTOs.FuelEnergyLog;

public class EmailFuelObserver implements FuelLogObserver {

    @Override
    public void onFuelLogAdded(FuelEnergyLog log) {
        String message = "🚨 Fuel Log Notification\n" +
                         "Vehicle ID: " + log.getVehicleId() + "\n" +
                         "Fuel: " + log.getFuelConsumed() + "\n" +
                         "Mileage: " + log.getMileage() + "\n" +
                         "Date: " + log.getLogDate() + "\n" +
                         "Notes: " + log.getNotes();

        // Simulate sending an email
        System.out.println("📧 Sending email to admin@example.com:\n" + message);
    }
}
