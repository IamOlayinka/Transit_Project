
<%@ page import="java.util.List" %>
<%@ page import="DTOs.MaintenanceSchedule" %>

<html>
<head>
    <title>Maintenance Schedule</title>
<style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
            background-color: #f8f9fa;
            color: #333;
        }

        h2, h3 {
            color: #2c3e50;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            font-weight: 500;
        }

        input[type="number"] {
            padding: 6px;
            width: 120px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            padding: 6px 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #dee2e6;
            text-align: center;
        }

        th {
            background-color: #e9ecef;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        .action-links a {
            margin: 0 5px;
        }

        .notice {
            margin-top: 10px;
            color: green;
        }

        .clear-link {
            margin-bottom: 10px;
            display: inline-block;
        }

        .add-maintenance-link {
            margin-top: 20px;
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 8px 16px;
            border-radius: 4px;
        }

        .add-maintenance-link:hover {
            background-color: #00008b;
        }
    </style>
</head>
<body>
<%@ include file="transitManagerHeader.jsp" %>
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

<a class="add-maintenance-link" href="maintenanceLogForm.jsp">Add New Log</a>

</body>
</html>
