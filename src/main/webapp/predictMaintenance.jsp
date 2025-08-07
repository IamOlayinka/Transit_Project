
<%@ page import="java.util.Map"%>
<%@ page import="DTOs.FuelEnergyLog"%>

<html>
<head>
<title>Predictive Maintenance</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, sans-serif;
	background-color: #f4f6f8;
	margin: 0;
	padding: 40px;
}

.main-wrapper {
	max-width: 900px;
	margin: auto;
	background-color: #fff;
	padding: 30px 40px;
	border-radius: 12px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.07);
}

h2 {
	text-align: center;
	color: #2c3e50;
	margin-bottom: 30px;
}

form {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 15px;
	margin-bottom: 30px;
}

label {
	font-weight: bold;
	color: #333;
}

select {
	padding: 8px 12px;
	font-size: 14px;
	border-radius: 6px;
	border: 1px solid #ccc;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	font-weight: bold;
	padding: 10px 18px;
	font-size: 15px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 25px;
}

th, td {
	padding: 12px 14px;
	border: 1px solid #ddd;
	text-align: center;
}

th {
	background-color: #007bff;
	color: white;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.back-link {
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	color: #007bff;
	font-weight: 500;
}

.back-link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main-wrapper">
		<h2>Predictive Maintenance</h2>

		<form
			action="${pageContext.request.contextPath}/PredictMaintenanceServlet"
			method="get">
			<label for="strategy">Select Strategy:</label> <select
				name="strategy" id="strategy">
				<option value="mileage">Mileage Based</option>
				<option value="fuel">Fuel Usage Based</option>
				<option value="interval">Interval Based (6 Months / 40,000
					km)</option>
			</select> <input type="submit" value="Run Prediction">
		</form>


		<%
    Map<FuelEnergyLog, String> predictions = (Map<FuelEnergyLog, String>) request.getAttribute("predictions");
    if (predictions != null && !predictions.isEmpty()) {
%>
		<h3>
			Results (Strategy:
			<%= request.getAttribute("strategyUsed") %>)
		</h3>
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

		<p>
			<a href="viewFuelEnergyLogs.jsp">Back to Logs</a>
		</p>
	</div>
</body>
</html>
