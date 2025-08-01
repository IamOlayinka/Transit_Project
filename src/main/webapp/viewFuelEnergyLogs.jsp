
<%@ page import="java.util.List" %>
<%@ page import="DTOs.FuelEnergyLog" %>

<html>
<head>
    <title>Fuel/Energy Logs</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
            background-color: #f8f9fa;
            color: #333;
        }

        h2, h3 {
            color: #2c3e50;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            font-weight: 500;
        }

        input[type="number"] {
            padding: 6px;
            width: 120px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            padding: 6px 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #dee2e6;
            text-align: center;
        }

        th {
            background-color: #e9ecef;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        .action-links a {
            margin: 0 5px;
        }

        .notice {
            margin-top: 10px;
            color: green;
        }

        .clear-link {
            margin-bottom: 10px;
            display: inline-block;
        }

        .add-log-link {
            margin-top: 20px;
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 8px 16px;
            border-radius: 4px;
        }

        .add-log-link:hover {
            background-color: #00008b;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
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
    <p class="notice">Showing logs for <strong>Vehicle ID: <%= filteredVehicleId %></strong></p>
    <p><a class="clear-link" href="ViewFuelEnergyLogsServlet">Clear Filter</a></p>
<%
    }
%>

<table>
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
        <td class="action-links">
            <a href="EditFuelEnergyLogServlet?id=<%= log.getId() %>">Edit</a>
            |
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

<a class="add-log-link" href="fuelEnergyLogForm.jsp">Add New Log</a>

</body>
</html>
