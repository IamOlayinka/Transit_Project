<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Vehicle</title>
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

<div class = "main-content">
	<div class="form-container">
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
    </div>
    </div>
</body>
</html>
