<%@ page import="DTOs.BreakLog"%>
<%@ page import="java.util.List"%>
<%@ page import="DTOs.UserDTO"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Break Logs</title>

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

input[type="number"], input[type="email"] {
    padding: 6px;
    width: 200px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    padding: 6px 14px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-weight: 500;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    margin-top: 20px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
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

.action-links a {
    display: inline-block;
    padding: 6px 10px;
    border-radius: 4px;
    font-size: 0.9rem;
    font-weight: 500;
    text-decoration: none;
    margin: 2px;
    color: white;
}

.btn-edit {
    background-color: #17a2b8;
}

.btn-edit:hover {
    background-color: #117a8b;
}

.btn-delete {
    background-color: #dc3545;
}

.btn-delete:hover {
    background-color: #a71d2a;
}

.notice {
    margin-top: 10px;
    color: green;
}

.clear-link {
    margin-bottom: 10px;
    display: inline-block;
}

.btn-primary {
    background-color: #007bff;
    color: white;
    padding: 8px 16px;
    border-radius: 6px;
    text-decoration: none;
    font-weight: bold;
    display: inline-block;
    margin-top: 20px;
}

.btn-primary:hover {
    background-color: #0056b3;
}

.btn {
    display: inline-block;
    padding: 6px 10px;
    border-radius: 4px;
    font-size: 0.9rem;
    font-weight: 500;
    text-decoration: none;
    margin: 2px;
    color: white;
    border: none;
    cursor: pointer;
}

.section {
    margin-bottom: 40px;
}

select {
    padding: 6px;
    border-radius: 4px;
}

.responsive-table {
    overflow-x: auto;
}
.duration-cell {
    font-weight: bold;
}
</style>
</head>
<body>
    <%@ include file="operatorHeader.jsp"%>

    <h2>Break Logs</h2>

    <%
    String message = (String) session.getAttribute("message");
    if (message != null) {
    %>
    <p style="color: green;"><%= message %></p>
    <%
        session.removeAttribute("message");
    }

    String error = request.getParameter("error");
    if ("unauthorized".equals(error)) {
    %>
    <p style="color: red;">Unauthorized: Only Managers can delete break logs.</p>
    <%
    }

    UserDTO user = (UserDTO) session.getAttribute("user");
    List<BreakLog> breakLogs = (List<BreakLog>) request.getAttribute("breakLogs");
    boolean hasOngoingBreak = false;
    BreakLog ongoingBreak = null;

    LocalDateTime now = LocalDateTime.now();

    // Check if current user has an ongoing break
    if (breakLogs != null && user != null) {
        for (BreakLog log : breakLogs) {
            if (log.getOperatorId() == user.getId() && (log.getBreakEndTime() == null || log.getBreakEndTime().isAfter(now))) {
                hasOngoingBreak = true;
                ongoingBreak = log;
                break;
            }
        }
    }
    %>

    <div class="responsive-table">
        <table>
            <tr>
                <th>ID</th>
                <th>Operator ID</th>
                <th>Vehicle ID</th>
                <th>Break Type</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Duration</th>
                <th>Status</th>
                <% if (user != null && "Manager".equalsIgnoreCase(user.getUserType())) { %>
                <th>Actions</th>
                <% } %>
            </tr>
            <%
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (breakLogs != null && !breakLogs.isEmpty()) {
                for (BreakLog log : breakLogs) {
                    String duration;
                    if (log.getDuration() > 0) {
                        duration = log.getDuration() + " mins";
                    } else if (log.getBreakEndTime() == null) {
                        duration = "Ongoing";
                    } else {
                        duration = "0";
                    }

                    String status;
                    if (log.getBreakEndTime() == null) {
                        status = "Ongoing";
                    } else if (log.getBreakEndTime().isAfter(now)) {
                        status = "Ongoing";
                    } else {
                        status = "Completed";
                    }

                    String rowClass = "status-" + (log.getBreakType().equalsIgnoreCase("break") ? "break" : "out_of_service");
            %>
            <tr class="<%= rowClass %>">
                <td><%= log.getId() %></td>
                <td><%= log.getOperatorId() %></td>
                <td><%= log.getVehicleId() != null ? log.getVehicleId() : "N/A" %></td>
                <td><%= log.getBreakType().equalsIgnoreCase("break") ? "Break" : "Out of Service" %></td>
                <td><%= log.getBreakStartTime().format(formatter) %></td>
                <td><%= log.getBreakEndTime() != null ? log.getBreakEndTime().format(formatter) : "Ongoing" %></td>
                <td class="duration-cell"><%= duration %></td>
                <td><%= status %></td>
                <% if (user != null && "Manager".equalsIgnoreCase(user.getUserType())) { %>
                <td>
                    <form action="DeleteBreakLog" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= log.getId() %>" />
                        <button class="btn btn-delete" type="submit"
                            onclick="return confirm('Are you sure you want to delete this break log?')">
                            Delete
                        </button>
                    </form>
                </td>
                <% } %>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="9">No break logs found.</td>
            </tr>
            <%
            }
            %>
        </table>
    </div>

    <div class="addnew" style="margin-top: 20px;">
        <%
        if (user != null) {
            if (hasOngoingBreak && ongoingBreak != null) {
                // Show Stop Break button only if there is an ongoing break
        %>
            <form action="StopBreakLogServlet" method="post" style="display: inline;">
                <input type="hidden" name="id" value="<%= ongoingBreak.getId() %>" />
                <button class="btn btn-primary" type="submit"
                    onclick="return confirm('Stop your current break now?')">Stop Break</button>
            </form>
        <%
            } else {
                // No ongoing break, show Start Break button
        %>
            <form action="AddBreakLogServlet" method="get" style="display: inline;">
                <button class="btn btn-primary" type="submit">Start Break</button>
            </form>
        <%
            }
        }
        %>
    </div>

</body>
</html>
