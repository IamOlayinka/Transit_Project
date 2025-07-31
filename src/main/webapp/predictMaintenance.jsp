<%@ include file="header.jsp" %>
<%@ page import="java.util.Map" %>
<%@ page import="DTOs.FuelEnergyLog" %>

<html>
<head>
    <title>Predictive Maintenance</title>
</head>
<body>
<h2>Predictive Maintenance</h2>

<form action="PredictMaintenanceServlet" method="get">
    <label for="strategy">Select Strategy:</label>
    <select name="strategy" id="strategy">
        <option value="mileage">Mileage Based</option>
        <option value="fuel">Fuel Usage Based</option>
        <option value="interval">Interval Based (6 Months / 40,000 km)</option>
    </select>
    <input type="submit" value="Run Prediction">
</form>

<%
    Map<FuelEnergyLog, String> predictions = (Map<FuelEnergyLog, String>) request.getAttribute("predictions");
    if (predictions != null && !predictions.isEmpty()) {
%>
    <h3>Results (Strategy: <%= request.getAttribute("strategyUsed") %>)</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Vehicle ID</th>
            <th>Log Date</th>
            <th>Mileage</th>
            <th>Fuel Consumed</th>
            <th>Prediction</th>
        </tr>
        <%
            for (Map.Entry<FuelEnergyLog, String> entry : predictions.entrySet()) {
                FuelEnergyLog log = entry.getKey();
                String recommendation = entry.getValue();
        %>
        <tr>
            <td><%= log.getVehicleId() %></td>
            <td><%= log.getLogDate() %></td>
            <td><%= log.getMileage() %></td>
            <td><%= log.getFuelConsumed() %></td>
            <td><%= recommendation %></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    }
%>

<p><a href="viewFuelEnergyLogs.jsp">Back to Logs</a></p>
</body>
</html>
