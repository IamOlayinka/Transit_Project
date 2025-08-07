<%@ page session="true"%>
<%@ page import="DTOs.UserDTO"%>
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
<meta charset="UTF-8">
<title>Welcome</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI', Tahoma, sans-serif;
	background: linear-gradient(135deg, #f0f4ff, #d4e1ff);
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.container {
	background: white;
	padding: 60px 50px;
	border-radius: 16px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
	max-width: 700px;
	text-align: center;
	animation: fadeIn 1s ease;
}

h1 {
	font-size: 2.5rem;
	color: #333;
	margin-bottom: 1rem;
}

.user-name {
	font-size: 2.8rem;
	font-weight: bold;
	color: #007bff;
}

p {
	font-size: 1.1rem;
	color: #555;
	margin: 1.5rem 0;
}

.btn {
	padding: 14px 28px;
	border: none;
	border-radius: 6px;
	font-size: 1rem;
	font-weight: 500;
	cursor: pointer;
	transition: all 0.3s ease;
	margin: 0 10px;
	text-decoration: none;
}

.btn-primary {
	background-color: #007bff;
	color: white;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn-outline {
	background-color: transparent;
	color: #007bff;
	border: 2px solid #007bff;
}

.btn-outline:hover {
	background-color: #007bff;
	color: white;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(30px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
@media ( max-width : 600px) {
	.container {
		padding: 30px 20px;
	}
	h1, .user-name {
		font-size: 2rem;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h1>Welcome Back,</h1>
		<div class="user-name"><%= user.getName() %></div>
		<br>
		<br> <a href="DashboardServlet" class="btn btn-primary">Go to
			Dashboard</a> <a href="logout" class="btn btn-outline">Logout</a>
	</div>
</body>
</html>
