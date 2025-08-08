<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="DTOs.UserDTO" %>

<% 
UserDTO user = (UserDTO) request.getSession().getAttribute("user");
if (user == null || !"Manager".equalsIgnoreCase(user.getUserType())) {
    // Unauthorized access
    response.sendRedirect("login.jsp"); 
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Vehicles</title>
<style>
body {
    font-family: Arial, sans-serif;
    padding: 20px;
}

h2 {
    text-align: center;
}

table {
    border-collapse: collapse;
    width: 100%;
    margin-top: 20px;
}

th, td {
    padding: 10px;
    border: 1px solid #ccc;
    text-align: center;
}

th {
    background-color: #f2f2f2;
}

a.button, button.delete-btn {
    background-color: #007BFF;
    color: white;
    padding: 6px 10px;
    text-decoration: none;
    border-radius: 4px;
    border: none;
    cursor: pointer;
}

a.button:hover, button.delete-btn:hover {
    background-color: #0056b3;
}

button.delete-btn {
    background-color: #dc3545;
}

button.delete-btn:hover {
    background-color: #c82333;
}

.action-form {
    display: inline;
    margin: 0 2px;
}
</style>
</head>
<body>
<jsp:include page="transitManagerHeader.jsp" />

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
<p style="color: red;">Unauthorized: Only Managers can delete vehicles.</p>
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
    <th>Assigned User ID</th>
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
    <td><%= v.getAssignedUserID() %></td>
    <td>
        <a class="button" href="editVehicle?id=<%= v.getId() %>">Edit</a>
        
        <%-- POST form for delete instead of GET link --%>
        <form class="action-form" action="deleteVehicle" method="post">
            <input type="hidden" name="id" value="<%= v.getId() %>">
            <button type="submit" class="delete-btn" 
                    onclick="return confirm('Are you sure you want to delete this vehicle?')">
                Delete
            </button>
        </form>
        
        <% if(user != null && "Manager".equalsIgnoreCase(user.getUserType())) { %>
        <%-- Additional manager actions can go here --%>
        <% } %>
    </td>
</tr>
<%
    }
%>
<tr>
    <td colspan="9">
        <a href="registerVehicle.jsp" class="button">Add New Vehicle</a>
    </td>
</tr>
<%
} else {
%>
<tr><td colspan="9">No vehicles found.</td></tr>
<%
}
%>
</table>

</body>
</html>