<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String logId = request.getParameter("id");
%>
<html>
<head>
<title>Confirm Delete</title>
</head>
<body>
	<h2>Confirm Deletion</h2>
	<p>
		Are you sure you want to delete fuel/energy log ID <strong><%= logId %></strong>?
	</p>

	<form action="DeleteFuelEnergyLogServlet" method="post">
		<input type="hidden" name="id" value="<%= logId %>" /> <input
			type="submit" value="Yes, Delete">
	</form>

	<br>
	<a href="ViewFuelEnergyLogsServlet">Cancel</a>
</body>
</html>
