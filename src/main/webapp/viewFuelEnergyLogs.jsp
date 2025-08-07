<%@ page import="java.util.List"%>
<%@ page import="DTOs.FuelEnergyLog"%>

<%@ include file="header.jsp"%>

<!DOCTYPE html>
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

input[type="number"], input[type="email"] {
	padding: 6px;
	width: 200px;
	margin-right: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	padding: 6px 14px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: 500;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	margin-top: 20px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
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

.action-links a {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 4px;
	font-size: 0.9rem;
	font-weight: 500;
	text-decoration: none;
	margin: 2px;
	color: white;
}

.btn-edit {
	background-color: #17a2b8;
}

.btn-edit:hover {
	background-color: #117a8b;
}

.btn-delete {
	background-color: #dc3545;
}

.btn-delete:hover {
	background-color: #a71d2a;
}

.notice {
	margin-top: 10px;
	color: green;
}

.clear-link {
	margin-bottom: 10px;
	display: inline-block;
}

.btn-primary {
	background-color: #007bff;
	color: white;
	padding: 8px 16px;
	border-radius: 6px;
	text-decoration: none;
	font-weight: bold;
	display: inline-block;
	margin-top: 20px;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 4px;
	font-size: 0.9rem;
	font-weight: 500;
	text-decoration: none;
	margin: 2px;
	color: white;
	border: none;
	cursor: pointer;
}

.section {
	margin-bottom: 40px;
}

select {
	padding: 6px;
	border-radius: 4px;
}

.responsive-table {
	overflow-x: auto;
}
</style>
</head>
<body>

	<div class="section">
		<h2>Fuel/Energy Logs</h2>

		<h3>Filter Logs by Vehicle ID</h3>
		<form action="ViewFuelEnergyLogsByVehicleServlet" method="get">
			<label for="vehicleId">Vehicle ID:</label> <input type="number"
				name="vehicleId" id="vehicleId" required> <input
				type="submit" value="Filter">
		</form>

		<%
            Object filteredVehicleId = request.getAttribute("filteredVehicleId");
            if (filteredVehicleId != null) {
                System.out.println("Displaying logs for filteredVehicleId: " + filteredVehicleId);
        %>
		<p class="notice">
			Showing logs for <strong>Vehicle ID: <%= filteredVehicleId %></strong>
		</p>
		<p>
			<a class="clear-link btn-primary" href="ViewFuelEnergyLogsServlet">Clear
				Filter</a>
		</p>
		<%
            } else {
                System.out.println("No filteredVehicleId attribute found");
            }
        %>

		<div class="responsive-table">
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
                    System.out.println("Retrieved logs: " + (logs != null ? logs.size() : "null"));
                    if (logs != null && !logs.isEmpty()) {
                        for (FuelEnergyLog log : logs) {
                            System.out.println("Processing log: ID=" + log.getId() + ", VehicleID=" + log.getVehicleId());
                %>
				<tr>
					<td><%= log.getId() %></td>
					<td><%= log.getVehicleId() %></td>
					<td><%= log.getLogDate() %></td>
					<td><%= log.getFuelConsumed() != null ? log.getFuelConsumed() : "-" %></td>
					<td><%= log.getEnergyConsumed() != null ? log.getEnergyConsumed() : "-" %></td>
					<td><%= log.getMileage() != null ? log.getMileage() : "-" %></td>
					<td><%= log.getNotes() != null ? log.getNotes() : "-" %></td>
					<td class="action-links"><a class="btn-edit"
						href="EditFuelEnergyLogServlet?id=<%= log.getId() %>">Edit</a>
						<form action="DeleteFuelEnergyLogServlet" method="post"
							style="display: inline;">
							<input type="hidden" name="id" value="<%= log.getId() %>" />
							<button class="btn btn-delete" type="submit"
								onclick="return confirm('Are you sure?')">Delete</button>
						</form></td>
				</tr>
				<%
                        }
                    } else {
                        System.out.println("No logs found or logs is null");
                %>
				<tr>
					<td colspan="8">No logs found.</td>
				</tr>
				<%
                    }
                %>
			</table>
		</div>

		<a class="btn-primary" href="fuelEnergyLogForm.jsp"> <i
			class="fa fa-plus-circle"></i> Add New Log
		</a>
	</div>

	<div class="section">
		<h2>Email Fuel Usage Report</h2>
		<form action="GenerateReportServlet" method="get">
			<label for="selectedLogId">Select Log Record:</label> <select
				name="logId" id="selectedLogId" required>
				<option value="" disabled selected>Select a log</option>
				<%
                    if (logs != null && !logs.isEmpty()) {
                        for (FuelEnergyLog log : logs) {
                            System.out.println("Adding log to dropdown: ID=" + log.getId());
                %>
				<option value="<%= log.getId() %>">Vehicle ID:
					<%= log.getVehicleId() %>, Date:
					<%= log.getLogDate() %>
				</option>
				<%
                        }
                    } else {
                        System.out.println("No logs available for dropdown");
                %>
				<option value="" disabled>No logs available</option>
				<%
                    }
                %>
			</select> <br>
			<br> <label for="email">Recipient Email:</label> <input
				type="email" name="email" placeholder="example@email.com" required>

			<input type="hidden" name="type" value="fuel"> <br>
			<br> <input type="submit" value="Email Report">
		</form>
	</div>

</body>
</html>