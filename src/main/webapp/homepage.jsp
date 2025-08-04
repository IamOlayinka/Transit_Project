<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PTFMS</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
<!-- Custom CSS (from Colorlib template) -->
<link href="css/style.css" rel="stylesheet">
</head>
<body>

	<!-- Header -->
	<header class="bg-dark text-white p-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<h1 class="h4 mb-0">PTFMS</h1>
			<nav>
				<a href="#home" class="text-white me-3">Home</a> 
				<a href="about.jsp" class="text-white me-3">About Us</a> 
				<a href="#services" class="text-white me-3">Services</a> 
				<a href="#contact" class="text-white">Contact</a> 
			</nav>
		</div>
	</header>

	<!-- Hero Section -->
	<section class="hero bg-primary text-white text-center p-5" id="home">
		<div class="container">
			<h2 class="display-4">Reliable Transportation Services</h2>
			<p class="lead">Connecting people from location to location
				efficiently</p>
			<a href="login.jsp" class="btn btn-light btn-lg mt-3">Get Started</a>
		</div>
	</section>

	<!-- Services Section -->
	<section class="bg-light p-5" id="services">
		<div class="container">
			<h3 class="text-center mb-4">Our Services</h3>
			<div class="row text-center">
				<div class="col-md-4">
					<i class="fas fa-truck fa-3x mb-3"></i>
					<h5>Transportation</h5>
					<p>Transportation across North America.</p>
				</div>
				<div class="col-md-4">
					<i class="fas fa-box-open fa-3x mb-3"></i>
					<h5>Maintenance service</h5>
					<p>Reliable maintenance services.</p>
				</div>
				<div class="col-md-4">
					<i class="fas fa-shipping-fast fa-3x mb-3"></i>
					<h5>Expedited Shipping</h5>
					<p>On-time delivery with real-time tracking.</p>
				</div>
			</div> 
		</div>
	</section>

	<!-- Contact Section -->
	<section class="p-5" id="contact">
		<div class="container">
			<h3 class="text-center mb-4">Contact Us</h3>
			<form class="row g-3" action="ContactServlet" method="post">
				<div class="col-md-6">
					<input type="text" class="form-control" name="name"
						placeholder="Your Name" required>
				</div>
				<div class="col-md-6">
					<input type="email" class="form-control" name="email"
						placeholder="Your Email" required>
				</div>
				<div class="col-12">
					<textarea class="form-control" name="message" rows="4"
						placeholder="Message" required></textarea>
				</div>
				<div class="col-12 text-center">
					<button type="submit" class="btn btn-primary">Send Message</button>
				</div>
			</form>
		</div>
	</section>

	<!-- Footer -->
	<footer class="bg-dark text-white text-center p-3 mt-5">
		<p>&copy; 2025 PTFMS. All Rights Reserved.</p>
	</footer>

	<!-- Bootstrap JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
