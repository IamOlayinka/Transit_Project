<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="DTOs.MaintenanceSchedule"%>
<%@ include file="header.jsp"%>

<!-- Style Block -->
<style>
.dashboard-container {
	max-width: 1200px;
	margin: auto;
	padding: 2rem;
	font-family: 'Segoe UI', sans-serif;
}

.dashboard-header h2 {
	font-size: 2rem;
	color: #2c3e50;
	margin-bottom: 2rem;
	border-left: 4px solid #007bff;
	padding-left: 1rem;
}

.stat-cards {
	display: flex;
	gap: 1.5rem;
	margin-bottom: 3rem;
	flex-wrap: wrap;
}

.card {
	background: linear-gradient(135deg, #f7f9fc, #e6efff);
	padding: 1.8rem;
	border-radius: 14px;
	box-shadow: 0 6px 16px rgba(0, 0, 0, 0.05);
	flex: 1 1 250px;
	position: relative;
	transition: transform 0.3s, box-shadow 0.3s;
	text-decoration: none;
	color: inherit;
}

.card:hover {
	transform: translateY(-4px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	background: linear-gradient(135deg, #e6f0ff, #d4e5ff);
}

.card h3 {
	margin: 0;
	color: #333;
	font-size: 1rem;
	margin-bottom: 0.5rem;
}

.card p {
	font-size: 2.2rem;
	font-weight: bold;
	color: #007bff;
	margin: 0;
}

.section-title {
	font-size: 1.4rem;
	margin: 2rem 0 1rem;
	color: #333;
	font-weight: 600;
	border-bottom: 2px solid #ddd;
	padding-bottom: 0.5rem;
}

.maintenance-table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.maintenance-table th, .maintenance-table td {
	padding: 1rem;
	text-align: center;
	border-bottom: 1px solid #eee;
	font-size: 0.95rem;
}

.maintenance-table th {
	background-color: #f1f3f5;
	color: #444;
	font-weight: 600;
}

.maintenance-table td {
	color: #333;
}

.maintenance-table td:last-child {
	font-weight: 600;
}

.status-Pending {
	color: #e67e22;
}

.status-Completed {
	color: #27ae60;
}

.status-Overdue {
	color: #c0392b;
}

.no-data {
	padding: 2rem;
	text-align: center;
	color: #999;
	font-style: italic;
}

@media ( max-width : 768px) {
	.stat-cards {
		flex-direction: column;
	}
}
</style>

<!-- Dashboard -->
<div class="dashboard-container">
	<div class="dashboard-header">
		<h2>Fleet Management Dashboard</h2>
	</div>

	<!-- Stat Cards -->
	<div class="stat-cards">
		<a href="VehicleList" class="card">
			<h3>Total Vehicles</h3>
			<p><%= request.getAttribute("vehicleCount") %></p>
		</a> <a href="ViewFuelEnergyLogsServlet" class="card">
			<h3>Total Fuel Logs</h3>
			<p><%= request.getAttribute("fuelLogCount") %></p>
		</a> <a href="ViewMaintenanceScheduleServlet" class="card">
			<h3>Completed Maintenances</h3>
			<p><%= request.getAttribute("completedMaintenances") %></p>
		</a>
	</div>

	<!-- Maintenance Due Table -->
	<div>
		<div class="section-title">🔧 Maintenance Due Soon</div>
		<table class="maintenance-table">
			<tr>
				<th>Vehicle ID</th>
				<th>Date</th>
				<th>Recommendation</th>
				<th>Status</th>
			</tr>
			<%
                List<MaintenanceSchedule> upcoming = (List<MaintenanceSchedule>) request.getAttribute("upcomingMaintenances");
                if (upcoming != null && !upcoming.isEmpty()) {
                    for (MaintenanceSchedule m : upcoming) {
            %>
			<tr>
				<td><%= m.getVehicleId() %></td>
				<td><%= m.getPredictedDate() %></td>
				<td><%= m.getRecommendation() %></td>
				<td class="status-<%= m.getStatus() %>"><%= m.getStatus() %></td>
			</tr>
			<%
                    }
                } else {
            %>
			<tr>
				<td colspan="4" class="no-data">No upcoming maintenance within
					7 days.</td>
			</tr>
			<%
                }
            %>
		</table>
	</div>
</div>

<%@ include file="footer.jsp"%>
