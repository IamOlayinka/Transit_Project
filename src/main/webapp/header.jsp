<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fleet Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        h2 {
            color: #2c3e50;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }
        th {
            background-color: #f5f5f5;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            padding: 6px 12px;
            margin: 2px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #3498db;
            color: white;
        }
        .btn-success {
            background-color: #2ecc71;
            color: white;
        }
        .btn-danger {
            background-color: #e74c3c;
            color: white;
        }
        .btn-warning {
            background-color: #f39c12;
            color: white;
        }
        .nav {
            margin-bottom: 30px;
        }
        .nav a {
            margin-right: 15px;
            text-decoration: none;
            color: #2c3e50;
        }
        .nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="nav">
    <a href="DashboardServlet">Dashboard</a>
    <a href="viewFuelEnergyLogs.jsp">Fuel Logs</a>
    <a href="ViewMaintenanceScheduleServlet">Maintenance</a>
    <a href="predictMaintenance.jsp">Predict Maintenance</a>
    <a href="vehicleList">Vehicles</a>
    
</div>
