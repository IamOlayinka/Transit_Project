🚍 Public Transit Fleet Management System (PTFMS)

A full-stack Java web application designed to manage and monitor a public transit fleet, built as a team project. The system enables managers and operators to log vehicles, track routes, predict maintenance, track fuel/energy usage and generate detailed reports.

✨ Features

🔑 Role-Based Access Control: Different privileges for managers and operators.

🚌 Vehicle Management: Log, update, and categorize vehicles (bus, light rail, diesel-electric train, etc.).

🔧 Predictive Maintenance: Track maintenance schedules and upcoming service needs.

🗺️ Route Management: Assign, view, and manage vehicle routes.

📊 Reporting System: Generate printable reports for vehicles, routes, and maintenance.

✅ JUnit Testing: Comprehensive unit tests to ensure reliability and robustness.

🏗️ Architecture

The system follows a 3-tier architecture for scalability and maintainability:

Presentation Layer – JSP pages for user interaction (login, dashboards, reports).

Application Layer – Servlets and Controllers handling requests, applying business rules, and enforcing roles.

Data Layer – MySQL database with DAO pattern for clean data access.

Design patterns used: Singleton, Builder, DAO, Front Controller, among others.

🛠️ Tech Stack

Backend: Java (Servlets, JSP)

Frontend: JSP, HTML, CSS, Bootstrap

Database: MySQL

Testing: JUnit

Design Patterns: Singleton, Builder, DAO, Front Controller
