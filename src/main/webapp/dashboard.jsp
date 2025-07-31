<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DTOs.MaintenanceSchedule" %>
<%@ include file="header.jsp" %>

<style>
    .dashboard-container {
        max-width: 1200px;
        margin: auto;
        padding: 2rem;
        font-family: 'Segoe UI', sans-serif;
    }

    .dashboard-header h2 {
        color: #2c3e50;
        margin-bottom: 2rem;
    }

    .stat-cards {
        display: flex;
        gap: 2rem;
        margin-bottom: 3rem;
        flex-wrap: wrap;
    }

    .card {
        background: #ffffff;
        padding: 1.5rem 2rem;
        border-radius: 12px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.06);
        flex: 1 1 30%;
        min-width: 250px;
    }

    .card h3 {
        margin: 0 0 0.5rem;
        color: #555;
        font-size: 1.1rem;
    }

    .card p {
        font-size: 1.8rem;
        font-weight: bold;
        color: #007bff;
    }

    .section-title {
        font-size: 1.4rem;
        margin: 2rem 0 1rem;
        color: #333;
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }

    .maintenance-table {
        width: 100%;
        border-collapse: collapse;
        background: white;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 1px 4px rgba(0,0,0,0.05);
    }

    .maintenance-table th, .maintenance-table td {
        padding: 1rem;
        text-align: center;
        border-bottom: 1px solid #eee;
    }

    .maintenance-table th {
        background-color: #f8f9fa;
        color: #555;
    }

    .maintenance-table td {
        color: #333;
    }

    .no-data {
        padding: 2rem;
        text-align: center;
        color: #777;
    }

    .quick-links {
        list-style: none;
        padding: 0;
        margin-top: 1rem;
    }

    .quick-links li {
        margin: 0.5rem 0;
    }

    .quick-links a {
        text-decoration: none;
        color: #007bff;
        font-weight: 500;
    }

    .quick-links a:hover {
        text-decoration: underline;
    }
</style>

<div class="dashboard-container">
    <div class="dashboard-header">
        <h2>Fleet Dashboard</h2>
    </div>

    <!-- Quick Stats -->
    <div class="stat-cards">
        <div class="card">
            <h3>Total Vehicles</h3>
            <p><%= request.getAttribute("vehicleCount") %></p>
        </div>
        <div class="card">
            <h3>Total Fuel Logs</h3>
            <p><%= request.getAttribute("fuelLogCount") %></p>
        </div>
        <div class="card">
            <h3>Completed Maintenances</h3>
            <p><%= request.getAttribute("completedMaintenances") %></p>
        </div>
    </div>

    <!-- Maintenance Table -->
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
                    <td><%= m.getStatus() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4" class="no-data">No upcoming maintenance within 7 days.</td>
                </tr>
            <%
                }
            %>
        </table>
    </div>

    <!-- Quick Links -->
    <div>
        <div class="section-title">📁 Quick Links</div>
        <ul class="quick-links">
            <li><a href="viewFuelEnergyLogs.jsp">View Fuel Logs</a></li>
            <li><a href="fuelEnergyLogForm.jsp">Add Fuel Log</a></li>
            <li><a href="ViewMaintenanceScheduleServlet">Maintenance Schedule</a></li>
            <li><a href="predictMaintenance.jsp">Run Maintenance Prediction</a></li>
        </ul>
    </div>
</div>

<%@ include file="footer.jsp" %>
