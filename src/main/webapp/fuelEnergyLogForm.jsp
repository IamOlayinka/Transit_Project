
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

	<h2>Add Fuel/Energy Log</h2>
	<form action="AddFuelEnergyLogServlet" method="post">
	    <label for="vehicleId">Vehicle ID:</label><br>
	    <input type="number" name="vehicleId" id="vehicleId" required><br><br>
	
	    <label for="logDate">Log Date and Time:</label><br>
	    <input type="datetime-local" name="logDate" id="logDate" required><br><br>
	
	    <label for="fuelConsumed">Fuel Consumed (liters or gallons):</label><br>
	    <input type="number" step="0.01" name="fuelConsumed" id="fuelConsumed"><br><br>
	
	    <label for="energyConsumed">Energy Consumed (kWh):</label><br>
	    <input type="number" step="0.01" name="energyConsumed" id="energyConsumed"><br><br>
	
	    <label for="mileage">Mileage (km or miles):</label><br>
	    <input type="number" step="0.01" name="mileage" id="mileage"><br><br>
	
	    <label for="notes">Notes:</label><br>
	    <textarea name="notes" id="notes" rows="3" cols="30"></textarea><br><br>
	
	    <input type="submit" value="Add Log">
	</form>

	<a class="back-link" href="ViewFuelEnergyLogsServlet">View All Logs</a>
	</div>
</div>
</body>
</html>
