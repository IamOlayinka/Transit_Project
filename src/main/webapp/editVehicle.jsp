<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Vehicle" %>

<%
    Vehicle v = (Vehicle) request.getAttribute("vehicle");
%>

<html>
<head>
    <title>Edit Vehicle</title>
</head>
<body>
    <h2>Edit Vehicle: <%= v.getVehicleNumber() %></h2>

    <form action="editVehicle" method="post">
        <input type="hidden" name="id" value="<%= v.getId() %>"/>

        Vehicle Number: <input type="text" name="vehicleNumber" value="<%= v.getVehicleNumber() %>" required/><br><br>
        Vehicle Type: <input type="text" name="vehicleType" value="<%= v.getVehicleType() %>" required/><br><br>
        Fuel Type: <input type="text" name="fuelType" value="<%= v.getFuelType() %>" required/><br><br>
        Consumption Rate: <input type="number" step="0.01" name="consumptionRate" value="<%= v.getConsumptionRate() %>" required/><br><br>
        Max Passengers: <input type="number" name="maxPassengers" value="<%= v.getMaxPassengers() %>" required/><br><br>
        Assigned Route: <input type="text" name="assignedRoute" value="<%= v.getAssignedRoute() %>" required/><br><br>

        <input type="submit" value="Update Vehicle"/>
    </form>
</body>
</html>
