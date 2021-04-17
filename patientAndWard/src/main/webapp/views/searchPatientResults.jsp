<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
	<!-- Navigation menu -->
	<div style="margin-bottom: 3px; background:#1976D2" class="ui inverted segment">
		<div style="border:none" class="ui inverted secondary pointing menu">
			<i onclick="window.location.href='<%=request.getContextPath()%>/views/HomePage.jsp'" class="hospital big black icon"></i>
			<div class="right menu">
					<a style="font-size:16px" class= "item" href="index.html">Services</a>
					<a style="font-size:16px" class= "item" href="index.html">About Us</a>
					<a style="font-size:16px" class= "item" href="index.html">Contact Us</a>
					<button class="ui image grey button">Log Out</button>
			</div>
		</div>
	</div>
	<!-- Navigation menu end -->
	
	<div class="page-wrapper">
		<!-- header start -->
		<div class="ui segment blue inverted headerFunction">
			<h2 class="headerTextFunction">Patient And Ward Management</h2>
		</div>
		<!-- header end -->
		
		<i onclick="window.location.href='<c:url value="/MainInterfaceController"/>'" class="angle double big left icon"></i>
		
		<!--  Display details of the patient searched-->
		<div class="ui centered grid">
			<div class="ui five wide column">
				<div style="margin-bottom: 20px; background-color: #448AFF"
					class="ui raised inverted segment">
					<h4 style="font-size: 20px" class="ui center aligned header">Patient's
						Details</h4>
					<div class="ui equal width grid">
						<div class="row">
							<div class="column">
								<label style="font-size: 16px; font-weight: bold; color:black">First
									Name</label>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getPatient_firstName()}</p>
							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Last Name</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getPatient_lastName()}</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Age</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getAge()} Years</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Admitted On</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getDateOfAdmission()}</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Address</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getAddress()}</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Contact Number</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getContactNo()}</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Cause of
									Admission</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientReturn.getCauseOfAdmission()}</p>

							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Unit</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientAdmittedUnit.getUnitName()}</p>
							</div>
						</div>
						<div class="row">
							<div class="column">
								<p style="font-size: 16px; font-weight: bold; color:black">Ward Number</p>
							</div>
							<div class="column">
								<p style="font-size: 16px; font-weight: bold">${PatientAdmittedWard.getWardNumber()}</p>
							</div>
						</div>

					</div>
					<div class="ui equal width grid">
						<div class="row">
							<div class="column">
								<button class="ui blue button">Update Details</button>
							</div>
							<div class="column">
								<form action="<c:url value="/DischargePatientController"/>"
									method="post">
									<input type="hidden" name="PatientID"
										value="${PatientReturn.getPatientId()}" />
									<button class="ui violet button" type="submit">Discharge
										Patient</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--  Display details of the patient searched end-->
	</div>

	<!-- modal to update patient details -->
	<div class="ui modal tiny modelUpdatePatientDetails">
		<i class="close icon"></i>
		<div class="header">Update Patient's Details</div>
		<div class="ui grid centered">
			<div class="column">
				<div class="ui segment">
					<form class="ui form" method="post"
						action="<c:url value="/updatePatientDetailsController"/>">
						<div class="field">
							<label>Patient's Name</label>
							<div class="two fields">
								<div class="field">
									<input type="text" name="update-patient-first-name"
										value="${PatientReturn.getPatient_firstName()}">
								</div>
								<div class="field">
									<input type="text" name="update-patient-last-name"
										value="${PatientReturn.getPatient_lastName()}">
								</div>
							</div>
						</div>

						<div class="two fields">
							<div class="field">
								<label>Age</label> <input type="text" name="update-patient-age"
									value="${PatientReturn.getAge()}">
							</div>
							<div class="field">
								<label>Contact Number</label> <input type="text"
									name="update-patient-contact"
									value="${PatientReturn.getContactNo()}">
							</div>
						</div>

						<div class="field">
							<label>Address</label> <input type="text"
								name="update-patient-address"
								value="${PatientReturn.getAddress()}">
						</div>


						<div class="field">
							<label>Cause of Admission</label> <input type="text"
								name="update-admitted-Cause"
								value="${PatientReturn.getCauseOfAdmission()}">
						</div>



						<div class="ui three column centered grid">
							<div class="column">
								<button class="ui blue button" type="submit">Update
									Details</button>
								<input type="hidden" name="PatientID"
									value="${PatientReturn.getPatientId()}" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end of update modal -->

	<!--  Footer start-->
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
					<a style="color: #BDBDBD" href="">Home</a> <br> <a
						style="color: #BDBDBD" href="">About Us</a> <br> <a
						style="color: #BDBDBD" href="">Contact Us</a>
				</p>
			</div>
		</div>
	</div>
	<!-- Footer end -->

	<script>
		history.pushState(null, document.title, location.href);
		window.addEventListener('popstate', function(event) {
			history.pushState(null, document.title, location.href);
		});
		
		$('.ui.blue.button').click(function() {
			$('.ui.modal.tiny.modelUpdatePatientDetails').modal('show');
			$('.ui.modal.tiny.modelUpdatePatientDetails').modal({backdrop: 'static', keyboard: false});
		});

	</script>
</body>
</html>