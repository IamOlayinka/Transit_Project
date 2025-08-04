<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            color: #1c1e21;
            margin-bottom: 1.5rem;
            font-size: 1.8rem;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 0.5rem;
            color: #606770;
            font-weight: 500;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 12px;
            margin-bottom: 1rem;
            border: 1px solid #dddfe2;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 1rem;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #1877f2;
            box-shadow: 0 0 0 2px #e7f3ff;
        }

        button {
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 1.1rem;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .login-button {
            background-color: #1877f2;
        }

        .login-button:hover {
            background-color: #00008B;
        }

        .bottom-link {
            text-align: center;
            margin-top: 1rem;
            font-size: 0.9rem;
        }

        .bottom-link a {
            color: #1877f2;
            text-decoration: none;
        }

        .bottom-link a:hover {
            text-decoration: underline;
        }

        .success-msg {
            text-align: center;
            color: green;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h1>Login</h1>

       <%
            String logout = request.getParameter("logout");
            if ("1".equals(logout)) {
        %>
            <div class="success-msg">You have successfully logged out.</div>
        <%
            }
        %>
       

        <form action="${pageContext.request.contextPath}/login"  method="post">
            Email: <input type="email" name="email" required><br>
            Password: <input type="password" name="password" required><br>
            <button type="submit" class="login-button">Login</button><br>
        </form>

        <div class="bottom-link">
            <p>Don't have an account? <a href="register.jsp">Click here to register</a></p>
        </div>
    </div>

</body>
</html>
