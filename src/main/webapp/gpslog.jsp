<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>

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
</head>
<body>

<h2>Log GPS Tracking for a Vehicle</h2>

<form action="gpsLog" method="post">
    Vehicle:
    <select name="vehicleId" required>
        <% for (Vehicle v : vehicles) { %>
            <option value="<%= v.getId() %>"><%= v.getVehicleNumber() %> - <%= v.getAssignedRoute() %></option>
        <% } %>
    </select><br><br>

    Station Name: <input type="text" name="stationName" required /><br><br>
    Arrival Time: <input type="datetime-local" name="arrivalTime" required /><br><br>
    Departure Time: <input type="datetime-local" name="departureTime" required /><br><br>

    <input type="submit" value="Submit GPS Log" />
</form>

</body>
</html>
