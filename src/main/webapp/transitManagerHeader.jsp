<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f8f9fa;
	color: #333;
}

.header {
	background-color: #fff;
	border-bottom: 1px solid #e9ecef;
	padding: 1rem 2rem;
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.nav-links {
	display: flex;
	gap: 2rem;
	list-style: none;
	margin: 0;
	padding: 0;
}

.nav-links a {
	text-decoration: none;
	color: #6c757d;
	font-weight: 500;
	padding: 0.5rem 0;
	border-bottom: 2px solid transparent;
	transition: all 0.3s ease;
}

.nav-links a:hover, .nav-links a.active {
	color: #007bff;
	border-bottom-color: #007bff;
}

.logo-section h2 {
	color: #333;
	margin: 0;
	font-weight: 600;
}

.logout-btn {
	background-color: #007bff;
	color: white;
	padding: 0.5rem 1rem;
	border: none;
	border-radius: 4px;
	text-decoration: none;
	font-weight: 500;
	transition: background-color 0.3s ease;
}

.logout-btn:hover {
	background-color: #0056b3;
}

.action-buttons {
	display: flex;
	gap: 1rem;
	justify-content: center;
	flex-wrap: wrap;
}

.btn {
	padding: 0.75rem 1.5rem;
	text-decoration: none;
	border-radius: 6px;
	font-weight: 500;
	transition: all 0.3s ease;
	border: 2px solid transparent;
}

.btn-primary {
	background-color: #007bff;
	color: white;
}

.btn-primary:hover {
	background-color: #0056b3;
	transform: translateY(-1px);
}

.btn-outline {
	background-color: transparent;
	color: #007bff;
	border-color: #007bff;
}

.btn-outline:hover {
	background-color: #007bff;
	color: white;
}

@media ( max-width : 768px) {
	.header {
		flex-direction: column;
		gap: 1rem;
		padding: 1rem;
	}
	.nav-links {
		gap: 1rem;
		flex-wrap: wrap;
		justify-content: center;
	}
	.welcome-card {
		padding: 2rem;
		margin: 1rem;
	}
	.welcome-card h1 {
		font-size: 2rem;
	}
	.action-buttons {
		flex-direction: column;
		align-items: center;
	}
	.btn {
		width: 200px;
	}
}
</style>
</head>
<body>
	<div class="header">
		<div class="logo-section">
			<h2>Transportation</h2>
		</div>

		<nav class="nav-links">
			<a href="index.jsp">Home</a> <a href="ManagerDashboardServlet">Dashboard</a>
			<a href="ViewFuelEnergyLogsServlet">Fuel Logs</a> <a
				href="ViewMaintenanceScheduleServlet">Maintenance</a> <a
				href="predictMaintenance.jsp">Predict Maintenance</a> <a
				href="VehicleList">Vehicles</a> <a href="Reports.jsp">Reports</a> <a
				href="gpslog.jsp">GPS Tracking</a>
		</nav>

		<a href="logout" class="logout-btn">Logout</a>
	</div>
</body>
</html>