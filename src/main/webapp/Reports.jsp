<%@ include file="transitManagerHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports Dashboard</title>


<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f4f6f8;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 800px;
	margin: 40px auto;
	padding: 20px;
	background: white;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h3 {
	font-size: 24px;
	color: #333;
	border-bottom: 2px solid #3498db;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

ul {
	list-style: none;
	padding-left: 0;
}

li {
	margin-bottom: 12px;
}

a {
	text-decoration: none;
	color: #3498db;
	font-weight: 500;
	transition: color 0.3s ease;
}

a:hover {
	color: #1d6fa5;
}

.section-title {
	font-size: 20px;
	margin-top: 30px;
	color: #555;
	border-left: 4px solid #2ecc71;
	padding-left: 10px;
	margin-bottom: 15px;
}
</style>
</head>
<body>

	<div class="container">
		<h3>Reports</h3>
		<ul class="report-links">
			<li><a href="GenerateReportServlet?type=fuel">Fuel Usage
					Report</a></li>
			<li><a href="GenerateReportServlet?type=maintenance">Maintenance
					Summary Report</a></li>
			<li><a href="GenerateReportServlet?type=fuel">View Fuel
					Report</a></li>
			<li><a href="GenerateReportServlet?type=maintenance">View
					Maintenance Report</a></li>
			<li><a href="ExportCSVServlet?type=fuel">Export Fuel Report
					to CSV</a></li>
			<li><a href="ExportCSVServlet?type=maintenance">Export
					Maintenance Report to CSV</a></li>
			<li><a href="ExportCSVServlet?type=fuel">Download Fuel
					Report (.csv)</a></li>
			<li><a href="ExportCSVServlet?type=maintenance">Download
					Maintenance Report (.csv)</a></li>
		</ul>

		<div class="section-title">Quick Links</div>
		<ul class="quick-links">
			<li><a href="viewFuelEnergyLogs.jsp">View Fuel Logs</a></li>
			<li><a href="fuelEnergyLogForm.jsp">Add Fuel Log</a></li>
			<li><a href="ViewMaintenanceScheduleServlet">Maintenance
					Schedule</a></li>
			<li><a href="predictMaintenance.jsp">Run Maintenance
					Prediction</a></li>
		</ul>
	</div>

</body>
</html>
