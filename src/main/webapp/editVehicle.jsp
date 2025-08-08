<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.Vehicle"%>
<%@ include file="transitManagerHeader.jsp"%>
<%@ page import="DTOs.UserDTO"%>


<% 
UserDTO user = (UserDTO) request.getSession().getAttribute("user");
if (user == null || !"Manager".equalsIgnoreCase(user.getUserType())) {
    // Unauthorized access
    response.sendRedirect("login.jsp"); 
    return;
}
%>

<%
    Vehicle v = (Vehicle) request.getAttribute("vehicle");
    if (v == null) {
%>
<div style="color: red; padding: 2rem;">Vehicle not found.</div>
<a href="vehicleList" class="btn btn-primary">Back to Vehicle List</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Vehicle</title>
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

h2 {
	color: #2c3e50;
	margin-bottom: 1.5rem;
}

form {
	background-color: white;
	padding: 2rem;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
	max-width: 600px;
}

label {
	font-weight: 500;
	display: block;
	margin-top: 1rem;
}

input[type="text"], input[type="number"] {
	width: 100%;
	padding: 10px;
	border-radius: 6px;
	border: 1px solid #ccc;
	margin-top: 6px;
}

input[type="submit"] {
	margin-top: 2rem;
	padding: 10px 20px;
	background-color: #007bff;
	color: white;
	font-weight: 500;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

.message {
	color: red;
	margin-bottom: 1rem;
}
</style>
</head>
<body>

	<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
	<div class="message"><%= message %></div>
	<%
        session.removeAttribute("message");
    }
%>
	<div class="main-content">
		<div class="form-container">
			<h2>
				Edit Vehicle:
				<%= v.getVehicleNumber() %></h2>
			<form action="editVehicle" method="post">
				<input type="hidden" name="id" value="<%= v.getId() %>" /> <label>Vehicle
					Number:</label> <input type="text" name="vehicleNumber"
					value="<%= v.getVehicleNumber() %>" required /> <label>Vehicle
					Type:</label> <input type="text" name="vehicleType"
					value="<%= v.getVehicleType() %>" required /> <label>Fuel
					Type:</label> <input type="text" name="fuelType"
					value="<%= v.getFuelType() %>" required /> <label>Consumption
					Rate:</label> <input type="number" step="0.1" name="consumptionRate"
					value="<%= v.getConsumptionRate() %>" required /> <label>Max
					Passengers:</label> <input type="number" name="maxPassengers"
					value="<%= v.getMaxPassengers() %>" required /> <label>Assigned
					Route:</label> <input type="text" name="assignedRoute"
					value="<%= v.getAssignedRoute() %>" required /> <label>Assigned
					User ID:</label> <input type="text" name="assignedUserID"
					value="<%= v.getAssignedUserID() %>" required /> 
					
					<input type="submit" value="Update Vehicle" />
			</form>
		</div>
	</div>
</body>
</html>
