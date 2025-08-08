<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="DTOs.BreakLog" %>
<%@ page import="DTOs.UserDTO" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<jsp:include page="operatorHeader.jsp" />

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

.status-table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.status-table th, .status-table td {
    padding: 1rem;
    text-align: center;
    border-bottom: 1px solid #eee;
    font-size: 0.95rem;
}

.status-table th {
    background-color: #f1f3f5;
    color: #444;
    font-weight: 600;
}

.status-table td {
    color: #333;
}

.status-Active {
    color: #27ae60;
}

.status-OnBreak {
    color: #e67e22;
}

.status-OutOfService {
    color: #c0392b;
}

.action-buttons {
    display: flex;
    gap: 1rem;
    margin: 1rem 0;
    flex-wrap: wrap;
}

.btn {
    padding: 0.6rem 1.2rem;
    border-radius: 6px;
    border: none;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s;
}

.btn-primary {
    background-color: #007bff;
    color: white;
}

.btn-primary:hover {
    background-color: #0069d9;
}

.btn-warning {
    background-color: #ffc107;
    color: #212529;
}

.btn-warning:hover {
    background-color: #e0a800;
}

.btn-danger {
    background-color: #dc3545;
    color: white;
}

.btn-danger:hover {
    background-color: #c82333;
}

.no-data {
    padding: 2rem;
    text-align: center;
    color: #999;
    font-style: italic;
}

@media (max-width: 768px) {
    .stat-cards {
        flex-direction: column;
    }
    .action-buttons {
        flex-direction: column;
    }
}
</style>

<div class="dashboard-container">
    <div class="dashboard-header">
        <h2>Operator Dashboard</h2>
    </div>

    <!-- Stat Cards -->
    <div class="stat-cards">
        <a href="MyVehicles" class="card">
            <h3>My Vehicles</h3>
            <p><%= request.getAttribute("vehicleCount") != null ? request.getAttribute("vehicleCount") : 0 %></p>
        </a>
        <a href="ViewFuelEnergyLogsServlet" class="card">
            <h3>Total Fuel Logs</h3>
            <p><%= request.getAttribute("fuelLogCount") != null ? request.getAttribute("fuelLogCount") : 0 %></p>
        </a>
    </div>

	<!-- Current Status -->
	<div class="section-title">Current Status</div>
	
	<%
	    BreakLog activeBreak = (BreakLog) request.getAttribute("activeBreak");
	    UserDTO user = (UserDTO) session.getAttribute("user");
	%>
	
	<div class="card clickable-card" onclick="window.location.href='BreakLogList'" style="cursor: pointer;">
	    <h3>Current Status</h3>
	    <%
	        if (activeBreak != null) {
	            String statusClass = "status-" + ("out_of_service".equals(activeBreak.getBreakType()) ? "OutOfService" : "OnBreak");
	    %>
	        <p class="<%= statusClass %>">
	            <%= "out_of_service".equals(activeBreak.getBreakType()) ? "Out of Service" : "On Break" %>
	        </p>
	        <p>Started at: <%= activeBreak.getBreakStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></p>
	        <%
	            if ("break".equals(activeBreak.getBreakType())) {
	                Duration duration = Duration.between(activeBreak.getBreakStartTime(), LocalDateTime.now());
	                long hours = duration.toHours();
	                long minutes = duration.toMinutesPart();
	        %>
	            <p>Duration: <%= hours %>h <%= minutes %>m</p>
	        <% } %>
	
	        <form action="OperatorDashboardServlet" method="post" style="margin-top: 1rem;" onclick="event.stopPropagation();">
	            <input type="hidden" name="action" value="endBreak" />
	            <button type="submit" class="btn btn-primary" onclick="return confirm('Stop current break?')">
	                End Break
	            </button>
	        </form>
	
	    <%
	        } else {
	    %>
	        <p class="status-Active">Active</p>
	
	        <a href="AddBreakLogServlet" class="btn btn-warning" style="text-decoration:none; display: inline-block; margin-top: 10px;" onclick="event.stopPropagation();">
	            Start Break
	        </a>
	    <%
	        }
	    %>
	</div>

    <!-- Today's Breaks Section -->
    <div class="section-title">Today's Breaks</div>
    <%
        List<BreakLog> todaysBreaks = (List<BreakLog>) request.getAttribute("todaysBreaks");
        if (todaysBreaks != null && !todaysBreaks.isEmpty()) {
    %>
        <table class="status-table">
            <tr>
                <th>Type</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Duration</th>
            </tr>
            <%
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                for (BreakLog breakLog : todaysBreaks) {
                    String breakType = "break".equals(breakLog.getBreakType()) ? "Break" : "Out of Service";
                    String duration = "Ongoing";
                    if (breakLog.getBreakEndTime() != null) {
                        Duration d = Duration.between(breakLog.getBreakStartTime(), breakLog.getBreakEndTime());
                        duration = String.format("%dh %dm", d.toHours(), d.toMinutesPart());
                    }
            %>
            <tr>
                <td><%= breakType %></td>
                <td><%= breakLog.getBreakStartTime().format(formatter) %></td>
                <td><%= breakLog.getBreakEndTime() != null ? breakLog.getBreakEndTime().format(formatter) : "Ongoing" %></td>
                <td><%= duration %></td>
            </tr>
            <% } %>
        </table>
    <% } else { %>
        <div class="no-data">No breaks recorded today</div>
    <% } %>
</div>

<jsp:include page="footer.jsp" />
