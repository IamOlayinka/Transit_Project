<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTOs.MaintenanceSchedule" %>

<html>
<head>
    <title>Maintenance Schedule</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        h2 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn { padding: 6px 12px; margin: 2px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-complete { background-color: #4CAF50; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
    </style>
</head>
<body>

<h2>Upcoming Maintenance Schedule</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Vehicle ID</th>
        <th>Predicted Date</th>
        <th>Strategy</th>
        <th>Recommendation</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <%
        List<MaintenanceSchedule> schedules = (List<MaintenanceSchedule>) request.getAttribute("schedules");
        if (schedules != null && !schedules.isEmpty()) {
            for (MaintenanceSchedule m : schedules) {
    %>
    <tr>
        <td><%= m.getId() %></td>
        <td><%= m.getVehicleId() %></td>
        <td><%= m.getPredictedDate() %></td>
        <td><%= m.getStrategyUsed() %></td>
        <td><%= m.getRecommendation() %></td>
        <td><%= m.getStatus() %></td>
        <td>
            <% if (!"Completed".equalsIgnoreCase(m.getStatus())) { %>
            <form action="MarkMaintenanceCompletedServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= m.getId() %>">
                <input type="submit" class="btn btn-complete" value="Mark Completed">
            </form>
            <% } %>

            <form action="DeleteMaintenanceServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= m.getId() %>">
                <input type="submit" class="btn btn-delete" value="Delete">
            </form>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="7">No maintenance schedules found.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
