
<!-- landing.jsp -->
<%@ include file="header.jsp" %>
<%@ page session="true" %>
<%@ page import="DTOs.UserDTO" %>

<%
UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body { font-family: Arial; text-align: center; padding-top: 100px; }
        h1 { color: #333; }
        a.button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4285f4;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a.button:hover {
            background-color: #3367d6;
        }
    </style>
<body>
    <h1>Welcome, <%= user.getName() %>!</h1>
    <p>This is your landing page.</p>
</body>
</html>