<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="login" method="post">
        <h5>Username:</h5>  <input type="text" name="username" required /><br/>
        <h5>Password:</h5> <input type="password" name="password" required /><br/>
        <input type="submit" value="Login" />
        <p style="color:red">${error}</p>
    </form>
</body>
</html>