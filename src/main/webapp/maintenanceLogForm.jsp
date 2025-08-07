<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Add Maintenance Schedule</title>
<style>
body {
	font-family: 'Segoe UI', sans-serif;
	margin: 30px;
	background-color: #f8f9fa;
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
}

form {
	max-width: 400px;
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin-bottom: 6px;
	margin-top: 12px;
	font-weight: 500;
}

input, select {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	margin-top: 16px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main-content">
		<div class="form-container">
			<h2>Add Maintenance Schedule</h2>

			<form action="AddMaintenanceLogServlet" method="post">
				<label for="vehicleId">Vehicle ID:</label> <input type="number"
					name="vehicleId" id="vehicleId" required> <label
					for="predictedDate">Predicted Date:</label> <input type="date"
					name="predictedDate" id="predictedDate" required> <label
					for="strategy">Strategy:</label> <select name="strategy"
					id="strategy">
					<option value="mileage">Mileage Based</option>
					<option value="interval">Interval Based</option>
					<option value="fuel">Fuel Usage Based</option>
					<option value="timeMileage">Time & Mileage Based</option>
				</select> <label for="recommendation">Recommendation:</label> <input
					type="text" name="recommendation" id="recommendation"> <label
					for="status">Status:</label> <select name="status" id="status">
					<option value="Pending">Pending</option>
					<option value="Completed">Completed</option>
				</select> <input type="submit" value="Save Schedule">
			</form>
		</div>
	</div>

</body>
</html>
