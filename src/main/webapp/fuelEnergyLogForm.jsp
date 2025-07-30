<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Fuel/Energy Log</title>
</head>
<body>
<h2>Add Fuel/Energy Log</h2>

<form action="AddFuelEnergyLogServlet" method="post">
    <label for="vehicleId">Vehicle ID:</label><br>
    <input type="number" name="vehicleId" id="vehicleId" required><br><br>

    <label for="logDate">Log Date and Time:</label><br>
    <input type="datetime-local" name="logDate" id="logDate" required><br><br>

    <label for="fuelConsumed">Fuel Consumed (liters or gallons):</label><br>
    <input type="number" step="0.01" name="fuelConsumed" id="fuelConsumed"><br><br>

    <label for="energyConsumed">Energy Consumed (kWh):</label><br>
    <input type="number" step="0.01" name="energyConsumed" id="energyConsumed"><br><br>

    <label for="mileage">Mileage (km or miles):</label><br>
    <input type="number" step="0.01" name="mileage" id="mileage"><br><br>

    <label for="notes">Notes:</label><br>
    <textarea name="notes" id="notes" rows="3" cols="30"></textarea><br><br>

    <input type="submit" value="Add Log">
</form>

<a href="ViewFuelEnergyLogsServlet">View All Logs</a>

</body>
</html>
