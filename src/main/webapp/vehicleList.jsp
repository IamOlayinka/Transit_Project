<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="DTOs.UserDTO" %>

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

        a.button {
            background-color: #007BFF;
            color: white;
            padding: 6px 10px;
            text-decoration: none;
            border-radius: 4px;
        }

        a.button:hover {
            background-color: #0056b3;
        }
        
        .addnew{
        margin-top: 20px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

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
        <td>
            <a class="button" href="editVehicle?id=<%= v.getId() %>">Edit</a>
            <form action="deleteVehicle" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= v.getId() %>"/>
           <button class="button" type="submit" onclick="return confirm('Are you sure?')">Delete</button>
    </form>
            
            <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            if(user != null && "Manager".equalsIgnoreCase(user.getUserType())) {
            %>
            
            
        </td>
    </tr>
    <%
            }
        }
        } 
        else {
    %>
    <tr><td colspan="8">No vehicles found.</td></tr>
    <%
        }
    %>
</table>
<div class="addnew">
<a href="registerVehicle.jsp" class="button">Add New Vehicle</a>  
</div>
    
</body>
</html>
