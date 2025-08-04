<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="Builder.GpsLog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>Add Fuel/Energy Log</title>
    <style>
    	body{
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

        input[type="number"],
        input[type="datetime-local"],
        textarea {
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
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class = "main-content">
	<div class="form-container">

	<h2>Add GPS Log</h2>
	<form action="GpsLogServlet" method="post">
	    <label for="vehicleId">Vehicle:</label>
            <select name="vehicleId" id="vehicleId" required>
                <% if (vehicles != null && !vehicles.isEmpty()) { %>
                    <% for (Vehicle v : vehicles) { %>
                        <option value="<%= v.getId() %>"><%= v.getVehicleNumber() %> - <%= v.getAssignedRoute() %></option>
                    <% } %>
                <% } else { %>
                    <option disabled>No vehicles available</option>
                <% } %>
            </select>

            <label for="stationName">Station Name:</label>
            <input type="text" name="stationName" id="stationName" required />

            <label for="arrivalTime">Arrival Time:</label>
            <input type="datetime-local" name="arrivalTime" id="arrivalTime" required />

            <label for="departureTime">Departure Time:</label>
            <input type="datetime-local" name="departureTime" id="departureTime" required />

            <input type="submit" value="Submit GPS Log">
	</form>

	<a class="back-link" href="gpslog.jsp">View All Logs</a>
	</div>
</div>
</body>
</html>
