<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hospital Management System</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/customStyles.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>


</head>
<body>
	<jsp:include page="/views/NavigationMenu.jsp"></jsp:include>
	<div class="main_header">
		<div class="ui one column grid">
			<div class="column">
				<h1 class="main_header_text">
					Hospital Management<br>System
				</h1>
			</div>
		</div>
	</div>



	<div style="padding: 40px; background-color: #2196F3;">
		<div class="ui four column grid">
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img src="<%=request.getContextPath()%>/assets/icons/doc-appt.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Doctor
							Appointment Scheduling</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/laboratory_management.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Laboratory<br>
							Management
						</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/patient_and_ward_management_2.jpg">
					</div>
					<div class="content">
						<a style="text-align:center"
							class="header" href="<%=request.getContextPath()%>/MainInterfaceController">Patient and Ward Management</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/pharmacy_and_medicine_management.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Pharmacy and
							Medicine<br> Management
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="ui four column grid">
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/payroll_management.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Payroll <br>
							Management
						</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/cash_and_billing.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Cash and Billing
							<br> management
						</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/inventory_and_stock.jpg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Inventory and
							Stock <br> Management
						</a>
					</div>
				</div>
			</div>
			<div class="column">
				<div class="ui card">
					<div class="image">
						<img
							src="<%=request.getContextPath()%>/assets/icons/administration.jpeg">
					</div>
					<div class="content">
						<a style="text-align: center" class="header">Administration <br>
							and Monitoring
						</a>
					</div>
				</div>
			</div>
		</div>

	</div>


	<div class="ui grid ">
		<div class="row footer">

			<div class="four wide column"></div>
			<div class="four wide column">
				<h5 class="footerMenuColor">Address</h5>
				<p class="footerMenuItemColor">
					SLIIT Malabe Campus, <br>New Kandy Road, <br>Malabe 10115
				</p>
			</div>
			<div class="four wide column">
				<h5 class="footerMenuColor">Contacts</h5>
				<p class="footerMenuItemColor">
					Email: support@hms.com <br>Phone: 011 2476498
				</p>
			</div>
			<div class="four wide column">
				<h5 class="footerMenuColor">Quick Links</h5>
				<p class="footerMenuItemColor">
					<a style= "color:#BDBDBD" href="">Home</a> <br>
					<a style= "color:#BDBDBD" href="">About Us</a> <br>
					<a style= "color:#BDBDBD" href="">Contact Us</a>
				</p>
			</div>
		</div>
	</div> 





</body>
</html>