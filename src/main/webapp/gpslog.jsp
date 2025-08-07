<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Vehicle"%>
<%@ page import="java.util.List"%>
<%@ page import="Builder.GpsLog"%>

<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
<p style="color: green;"><%= message %></p>
<%
        session.removeAttribute("message");
    }

    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>

<html>
<head>
<title>GPS Log Form</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, sans-serif;
	background-color: #f4f6f8;
	margin: 0;
	padding: 40px;
	justify-content: center;
}

.form-container {
	background-color: #fff;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 600px;
}

.main-content {
	display: flex;
	justify-content: center;
	align-items: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 30px 0;
	background-color: white;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

th, td {
	padding: 12px 15px;
	border: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #007bff;
	color: white;
}

h2 {
	text-align: center;
	color: #333;
	margin-bottom: 25px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: 500;
	color: #333;
}

input[type="number"], input[type="datetime-local"], textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 18px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
}

textarea {
	resize: vertical;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 12px 20px;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
	width: 100%;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

.back-link {
	display: block;
	text-align: center;
	margin-top: 20px;
	text-decoration: none;
	color: #007bff;
	font-weight: 500;
}

.back-link:hover {
	text-decoration: underline;
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
	<%@ include file="header.jsp"%>

	<h3>All GPS Logs</h3>
	<table>
		<tr>
			<th>ID</th>
			<th>Vehicle ID</th>
			<th>Station</th>
			<th>Arrival Time</th>
			<th>Departure Time</th>
			<th>Logged By</th>
		</tr>
		<%
        List<Builder.GpsLog> logs = (List<Builder.GpsLog>) request.getAttribute("logs");
        if (logs != null && !logs.isEmpty()) {
            for (Builder.GpsLog log : logs) {
    %>
		<tr>
			<td><%= log.getId() %></td>
			<td><%= log.getVehicleId() %></td>
			<td><%= log.getStationName() %></td>
			<td><%= log.getArrivalTime() %></td>
			<td><%= log.getDepartureTime() %></td>
			<td><%= log.getLoggedBy() %></td>
		</tr>
		<%
            }
        } else {
    %>
		<tr>
			<td colspan="6">No GPS logs available.</td>
		</tr>
		<br>

		<%
        }
    %>
	</table>
	<a class="add-log-link" href="AddGpsLogtoList">Add New Log</a>


</body>
</html>
