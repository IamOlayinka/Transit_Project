
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, DTOs.MaintenanceSchedule, model.Vehicle, DTOs.FuelEnergyLog" %>
<%@ include file="header.jsp" %>

<h2>🚗 Fleet Dashboard</h2>

<!-- Quick Stats -->
<div style="display: flex; gap: 20px; margin-bottom: 30px;">
    <div style="padding: 20px; background: #f8f9fa; border-radius: 6px;">
        <h3>Total Vehicles</h3>
        <p><strong><%= request.getAttribute("vehicleCount") %></strong></p>
    </div>
    <div style="padding: 20px; background: #f8f9fa; border-radius: 6px;">
        <h3>Total Fuel Logs</h3>
        <p><strong><%= request.getAttribute("fuelLogCount") %></strong></p>
    </div>
    <div style="padding: 20px; background: #f8f9fa; border-radius: 6px;">
        <h3>Completed Maintenances</h3>
        <p><strong><%= request.getAttribute("completedMaintenances") %></strong></p>
    </div>
</div>

<!-- Upcoming Maintenance -->
<h3>🔧 Maintenance Due Soon</h3>
<table>
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
    <tr><td colspan="4">No upcoming maintenance within 7 days.</td></tr>
    <%
        }
    %>
</table>

<!-- Shortcuts -->
<h3 style="margin-top: 40px;">📁 Quick Links</h3>
<ul>
    <li><a href="viewFuelEnergyLogs.jsp">View Fuel Logs</a></li>
    <li><a href="fuelEnergyLogForm.jsp">Add Fuel Log</a></li>
    <li><a href="ViewMaintenanceScheduleServlet">Maintenance Schedule</a></li>
    <li><a href="predictMaintenance.jsp">Run Maintenance Prediction</a></li>
</ul>

<%@ include file="footer.jsp" %>
