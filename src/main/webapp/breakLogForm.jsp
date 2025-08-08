<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="DTOs.UserDTO" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
<title>Start Break</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, sans-serif;
    background-color: #f4f6f8;
    margin: 0;
    padding: 40px;
    justify-content: center;
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
    text-align: center;
    color: #333;
    margin-bottom: 25px;
}

label {
    display: block;
    margin-bottom: 6px;
    font-weight: 500;
    color: #333;
}

input[type="number"], 
input[type="datetime-local"], 
select,
textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 18px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}

textarea {
    resize: vertical;
}

input[type="submit"] {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    width: 100%;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

.back-link {
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
    color: #007bff;
    font-weight: 500;
}

.back-link:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
    <%@ include file="transitManagerHeader.jsp"%>
    <div class="main-content">
        <div class="form-container">

            <h2>Start Break</h2>
            <form action="AddBreakLogServlet" method="post">
                <%
                UserDTO user = (UserDTO) session.getAttribute("user");
                if (user != null) {
                %>
                <input type="hidden" name="operatorId" value="<%= user.getId() %>">
                <% } %>
                
                <label for="breakType">Break Type:</label><br>
                <select name="breakType" id="breakType" required>
                    <option value="break">Regular Break</option>
                    <option value="out_of_service">Out of Service</option>
                </select><br><br>
                
                <label for="startTime">Start Time:</label><br>
                <input type="datetime-local" name="startTime" id="startTime" 
                    value="<%= LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) %>" required><br><br>
                
                <label for="duration">Duration (minutes):</label><br>
				<input type="number" name="duration" id="duration" min="1" max="30" required><br><br>
                
                <label for="vehicleId">Vehicle (if applicable):</label><br>
                <input type="number" name="vehicleId" id="vehicleId"><br><br>
                
                <label for="notes">Notes:</label><br>
                <textarea name="notes" id="notes" rows="3" cols="30" placeholder="Optional notes about the break..."></textarea><br><br>
                
                <input type="submit" value="Start Break">
            </form>

            <a class="back-link" href="BreakLogList">View All Break Logs</a>
        </div>
    </div>
</body>
</html>
