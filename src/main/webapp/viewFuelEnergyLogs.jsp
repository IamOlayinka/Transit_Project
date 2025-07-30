<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTOs.FuelEnergyLog" %>

<html>
<head>
    <title>Fuel/Energy Logs</title>
</head>
<body>
<h2>Fuel/Energy Logs</h2>

<h3>Filter Logs by Vehicle ID</h3>
<form action="ViewFuelEnergyLogsByVehicleServlet" method="get">
    <label for="vehicleId">Vehicle ID:</label>
    <input type="number" name="vehicleId" id="vehicleId" required>
    <input type="submit" value="Filter">
</form>

<%
    Object filteredVehicleId = request.getAttribute("filteredVehicleId");
    if (filteredVehicleId != null) {
%>
    <p>Showing logs for <strong>Vehicle ID: <%= filteredVehicleId %></strong></p>
    <p><a href="ViewFuelEnergyLogsServlet">Clear Filter</a></p>
<%
    }
%>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Vehicle ID</th>
        <th>Log Date</th>
        <th>Fuel Consumed</th>
        <th>Energy Consumed</th>
        <th>Mileage</th>
        <th>Notes</th>
        <th>Actions</th>
    </tr>

    <%
        List<FuelEnergyLog> logs = (List<FuelEnergyLog>) request.getAttribute("logs");
        if (logs != null && !logs.isEmpty()) {
            for (FuelEnergyLog log : logs) {
    %>
    <tr>
        <td><%= log.getId() %></td>
        <td><%= log.getVehicleId() %></td>
        <td><%= log.getLogDate() %></td>
        <td><%= log.getFuelConsumed() != null ? log.getFuelConsumed() : "-" %></td>
        <td><%= log.getEnergyConsumed() != null ? log.getEnergyConsumed() : "-" %></td>
        <td><%= log.getMileage() != null ? log.getMileage() : "-" %></td>
        <td><%= log.getNotes() != null ? log.getNotes() : "-" %></td>
        <td>
            <a href="EditFuelEnergyLogServlet?id=<%= log.getId() %>">Edit</a> |
            <a href="confirmDeleteLog.jsp?id=<%= log.getId() %>">Delete</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr><td colspan="8">No logs found.</td></tr>
    <%
        }
    %>
</table>

<br><a href="fuelEnergyLogForm.jsp">Add New Log</a>

</body>
</html>
