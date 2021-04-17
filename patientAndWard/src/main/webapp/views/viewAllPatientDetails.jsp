<%@page import="patientAndWard.entities.PatientAdmitted"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>
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
	<!-- Navigation menu start -->
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

		<!-- Table that displays all the details of admitted patients -->
		<table style="background-color: #757575; margin-bottom: 60px"
			class="ui collapsing inverted table tableStyle">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Contact Number</th>
					<th>Address</th>
					<th>Unit</th>
					<th>Ward</th>
					<th>Cause Of Admission</th>
					<th>Admitted Date</th>
					<th>Discharged Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ListOfAllPatients}" var="PatientList">
					<tr>
						<td>${PatientList.getPatient_firstName()}</td>
						<td>${PatientList.getPatient_lastName()}</td>
						<td>${PatientList.getAge()}</td>
						<td>${PatientList.getContactNo()}</td>
						<td>${PatientList.getAddress()}</td>
						<td>${PatientList.getWardPatient().getUnitWard().getUnitName()}</td>
						<td>${PatientList.getWardPatient().getWardNumber()}</td>
						<td>${PatientList.getCauseOfAdmission()}</td>
						<td>${PatientList.getDateOfAdmission()}</td>
						<td>${PatientList.getDateDischarged()}</td>
						<td>
							<form method="POST"
								action="${pageContext.request.contextPath}/viewAllPatientDetailsController">
								<input type="hidden" name="PatientID"
									value="${PatientList.getPatientId()}"></input>
								<button class="ui blue button" type="submit">Delete
									Patient Record</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- end of the table  -->
	</div>
	
	<!-- Get delete success or failure message -->
	<div id="DeletePatientRecord" class="displayControl">${deleteRecordError}</div>
	<div id="DeletePatientRecordSuccess" class="displayControl">${deleteRecordSuccess}</div>
	<!--  -->

	<!-- modal to display success of the delete of patient record -->
	<div class="ui tiny modal deletePatientRecordErrorModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">${deleteRecordError}</div>
				<p>You must discharge patient before deleting the patient record</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- modal to display failure of the delete of patient record-->
	<div class="ui tiny modal deletePatientRecordSuccessModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui positive message">
				<div class="header">${deleteRecordSuccess}</div>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- Footer start -->
	<div class="ui grid">
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
			
	var deletePatientRecord = $('#DeletePatientRecord').html();
	var deletePatientRecordSuccess = $('#DeletePatientRecordSuccess')
					.html();

	if (deletePatientRecord === '') {
		$('.ui.tiny.modal.deletePatientRecordErrorModal').modal('hide');
	} else {
		$('.ui.tiny.modal.deletePatientRecordErrorModal').modal('show');
		$('.ui.tiny.modal.deletePatientRecordErrorModal').modal({backdrop: 'static', keyboard: false});
	}

	if (deletePatientRecordSuccess === '') {
		$('.ui.tiny.modal.deletePatientRecordSuccessModal').modal('hide');
	} else {
		$('.ui.tiny.modal.deletePatientRecordSuccessModal').modal('show');
		$('.ui.tiny.modal.deletePatientRecordSuccessModal').modal({backdrop: 'static', keyboard: false});
	}
	</script>

</body>
</html>