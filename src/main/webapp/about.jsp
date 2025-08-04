<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About - Public Transit Fleet Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Optional custom styles -->
    <style>
        body {
            padding-top: 60px;
        }
        .about-section {
            background: #f8f9fa;
            border-radius: 12px;
            padding: 40px;
            box-shadow: 0 0 20px rgba(0,0,0,0.05);
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="homepage.jsp">PTFMS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="homepage.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link active" href="about.jsp">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- About Content -->
<div class="container">
    <div class="about-section mt-5">
        <h2 class="mb-3 text-center">About Our Transit Fleet System</h2>
        <p>
            This Public Transit Fleet Management System is a web-based solution for managing vehicles 
            such as buses, light rails, and trains. It allows for real-time GPS tracking, maintenance monitoring, 
            fuel/electricity logging, and personnel coordination.
        </p>
        <p>
            Built with Java Servlets, JSP, MySQL, and MVC architecture, the system ensures streamlined 
            transit operations for both administrators and operators.
        </p>
        <p>
            Features include:
        </p>
        <ul>
            <li>GPS location logging</li>
            <li>Vehicle & personnel management</li>
            <li>Energy/fuel usage tracking</li>
            <li>Predictive maintenance alerting</li>
            <li>User-friendly dashboards & reporting</li>
        </ul>
        <p class="text-muted mt-4">Developed by Olayinka Idumu, Abdullahi Salami, Amindu Udawatta and Mustafa Tarek | Prof Gustavo Adami - CST8288 - Object-Oriented Programming with Design Patterns</p>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
