<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Vehicle</title>
</head>
<body>
    <h2>Vehicle Registration Form</h2>
    
    <%
    String message = (String) session.getAttribute("message");
    if (message != null) {
    %>
    <p style="color: green;"><%= message %></p>
    <%
        session.removeAttribute("message");
    }
    %>
    

    <% if (request.getParameter("success") != null) { %>
        <p style="color:green;">Vehicle registered successfully!</p>
    <% } else if (request.getParameter("error") != null) { %>
        <p style="color:red;">Error registering vehicle. Please try again.</p>
    <% } %>

    <form action="registerVehicle" method="post">
        Vehicle Number: <input type="text" name="vehicleNumber" required /><br><br>

        Vehicle Type:
        <select name="vehicleType" required>
            <option value="Diesel Bus">Diesel Bus</option>
            <option value="Electric Light Rail">Electric Light Rail</option>
            <option value="Diesel-Electric Train">Diesel-Electric Train</option>
        </select><br><br>

        Fuel/Energy Type: <input type="text" name="fuelType" required /><br><br>
        Consumption Rate: <input type="number" step="0.01" name="consumptionRate" required /><br><br>
        Max Passengers: <input type="number" name="maxPassengers" required /><br><br>
        Assigned Route: <input type="text" name="assignedRoute" required /><br><br>

        <input type="submit" value="Register Vehicle" />
    </form>
</body>
</html>
