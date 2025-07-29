<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>

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
    </style>
</head>
<body>

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
            <a class="button" href="deleteVehicle?id=<%= v.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr><td colspan="8">No vehicles found.</td></tr>
    <%
        }
    %>
</table>

</body>
</html>
