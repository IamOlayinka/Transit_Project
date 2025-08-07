<%@ page import="model.Vehicle"%>
<%@ page import="java.util.List"%>
<%@ page import="DTOs.UserDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Vehicles</title>

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
	<%@ include file="header.jsp"%>

	<h2>Registered Vehicles</h2>

	<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
	<p style="color: green;"><%= message %></p>
	<%
        session.removeAttribute("message");
    }

    String error = request.getParameter("error");
    if ("unauthorized".equals(error)) {
%>
	<p style="color: red;">Unauthorized: Only Managers can delete
		vehicles.</p>
	<%
    }
%>

	<table>
		<tr>
			<th>ID</th>
			<th>Number</th>
			<th>Type</th>
			<th>Fuel Type</th>
			<th>Consumption</th>
			<th>Passengers</th>
			<th>Route</th>
			<th>Actions</th>
		</tr>
		<%
        List<model.Vehicle> vehicles = (List<model.Vehicle>) request.getAttribute("vehicles");
        if (vehicles != null && !vehicles.isEmpty()) {
            for (model.Vehicle v : vehicles) {
    %>
		<tr>
			<td><%= v.getId() %></td>
			<td><%= v.getVehicleNumber() %></td>
			<td><%= v.getVehicleType() %></td>
			<td><%= v.getFuelType() %></td>
			<td><%= v.getConsumptionRate() %></td>
			<td><%= v.getMaxPassengers() %></td>
			<td><%= v.getAssignedRoute() %></td>
			<td><a class="btn btn-edit"
				href="editVehicle?id=<%= v.getId() %>">Edit</a>

				<form action="deleteVehicle" method="post" style="display: inline;">
					<input type="hidden" name="id" value="<%= v.getId() %>" />
					<button class="btn btn-delete" type="submit"
						onclick="return confirm('Are you sure?')">Delete</button>
				</form> <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            if(user != null && "Manager".equalsIgnoreCase(user.getUserType())) {
            %></td>
		</tr>
		<%
            }
        }
        } 
        else {
    %>
		<tr>
			<td colspan="8">No vehicles found.</td>
		</tr>
		<%
        }
    %>
	</table>
	<div class="addnew">
		<a href="registerVehicle.jsp" class="btn btn-primary">Add New
			Vehicle</a>
	</div>

</body>
</html>
