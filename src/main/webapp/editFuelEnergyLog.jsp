<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import= "DTOs.FuelEnergyLog" %>

<%
    FuelEnergyLog log = (FuelEnergyLog) request.getAttribute("log");
%>

<html>
<head>
    <title>Edit Fuel/Energy Log</title>
</head>
<body>
<h2>Edit Fuel/Energy Log</h2>

<form action="UpdateFuelEnergyLogServlet" method="post">
    <input type="hidden" name="id" value="<%= log.getId() %>"/>

    <label for="vehicleId">Vehicle ID:</label><br>
    <input type="number" name="vehicleId" id="vehicleId" value="<%= log.getVehicleId() %>" required><br><br>

    <label for="logDate">Log Date and Time:</label><br>
    <input type="datetime-local" name="logDate" id="logDate"
           value="<%= log.getLogDate().toString().replace('T', ' ').substring(0, 16).replace(' ', 'T') %>" required><br><br>

    <label for="fuelConsumed">Fuel Consumed:</label><br>
    <input type="number" step="0.01" name="fuelConsumed" id="fuelConsumed"
           value="<%= log.getFuelConsumed() != null ? log.getFuelConsumed() : "" %>"><br><br>

    <label for="energyConsumed">Energy Consumed:</label><br>
    <input type="number" step="0.01" name="energyConsumed" id="energyConsumed"
           value="<%= log.getEnergyConsumed() != null ? log.getEnergyConsumed() : "" %>"><br><br>

    <label for="mileage">Mileage:</label><br>
    <input type="number" step="0.01" name="mileage" id="mileage"
           value="<%= log.getMileage() != null ? log.getMileage() : "" %>"><br><br>

    <label for="notes">Notes:</label><br>
    <textarea name="notes" id="notes" rows="3" cols="30"><%= log.getNotes() != null ? log.getNotes() : "" %></textarea><br><br>

    <input type="submit" value="Update Log">
</form>

<a href="ViewFuelEnergyLogsServlet">Back to Logs</a>
</body>
</html>
