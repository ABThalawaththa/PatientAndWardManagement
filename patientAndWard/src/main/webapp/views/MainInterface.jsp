<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%-- Header Style--%>


<%-- Header Style end --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/customStyles.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/patientDetailsInputValidation.js"></script>

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
					<button class="ui image grey button">Log out</button>
			</div>
		</div>
	</div>
	<!--  Navigation menu start-->

	<div class="page-wrapper">

		<!-- Header start -->
		<div class="ui segment blue inverted headerFunction">
			<h2 class="headerTextFunction">Patient And Ward Management</h2>
		</div>
		<!-- Header end -->
		
		<!-- Patien and ward management main interface start -->
		<div class="ui three column centered grid">
			<div class="column">
				<div class="ui red segment two column centered grid">
					<div class="column">
						<button class="ui red inverted button addUnitButton">
							<p style="font-size: 20px">Add New Unit</p>
						</button>
					</div>
				</div>

				<div class="ui orange segment two column centered grid">
					<div class="column">
						<button class="ui orange inverted button admitPatientButton">
							<p style="font-size: 20px">Patient Admission</p>
						</button>
					</div>
				</div>

				<div class="ui blue segment two column centered grid">
					<div class="column">
						<button class="ui blue inverted button"
							onclick="window.location.href='<c:url value="/viewAllPatientDetailsController"/>'">
							<p style="font-size: 20px">View details of all patients</p>
						</button>
					</div>
				</div>

				<div class="ui purple segment two column centered grid">
					<div class="column">
						<button class="ui purple inverted button SearchPatientButton">
							<p style="font-size: 20px">Search details of not yet discharged patients</p>
						</button>
					</div>
				</div>

				<div class="ui brown segment two column centered grid">
					<div class="column">
						<button class="ui brown inverted button GenerateReportButton">
							<p style="font-size: 20px">Download Patients' Details Report</p>
						</button>
					</div>
				</div>
			</div>
		</div>
		<!--  Patient and Ward management main interface end-->

		<!-- Modal to get patient details -->
		<div class="ui modal tiny modelPatientDetails ">
			<i class="close icon"></i>
			<div style="text-align: center" class="header">Enter Patient's
				Details</div>
			<div class="ui grid centered">
				<div class="column">
					<div class="ui segment">
						<form id="PatientDetailsInput" class="ui form" method="post"
							action="<c:url value="/patientInputController"/>">
							<div class="field">
								<label>Patient's Name</label>
								<div class="two fields">
									<div class="field">
										<input type="text" name="patient_first_name"
											pattern="^[a-zA-Z]+$"
											title='first name must contain only letters'
											placeholder="First Name" autocomplete="off" required>
									</div>
									<div class="field">
										<input type="text" name="patient_last_name"
											pattern='^[a-zA-Z]+$'
											title='last name must contain only letters'
											placeholder="Last Name" required>
									</div>
								</div>
							</div>

							<div class="two fields">
								<div class="field">
									<label>Age</label> <input type="text" name="patient_age"
										pattern='[1-9]{2}' title="Please enter valid age"
										placeholder="Age" autocomplete="off" required>
								</div>
								<div class="field">
									<label>Contact Number</label> <input type="text"
										name="patient_contact" placeholder="Contact Number"
										pattern="[0][0-9]{9}" title='Enter a valid phone number'
										required>
								</div>
							</div>

							<div class="field">
								<label>Address</label> <input type="text" name="patient_address"
									placeholder="Address" required>
							</div>

							<div class="field">
								<label>Admitted By</label>
								<div class="two fields">
									<div class="field">
										<input type="text" name="Admittedby_first-name"
											placeholder="First Name" pattern="^[a-zA-Z]+$"
											title='first name must contain only letters' required>
									</div>
									<div class="field">
										<input type="text" name="Admittedby_last-name"
											placeholder="Last Name" pattern="^[a-zA-Z]+$"
											title='last name must contain only letters' required>
									</div>
								</div>
							</div>

							<div class="field">
								<label>Cause of Admission</label> <input type="text"
									name="admitted_Cause" placeholder="Cause of admission">
							</div>

							<div class="two fields">
								<div class="field">
									<label>Unit</label> <select class="ui fluid dropdown"
										name="unitAdmitted">
										<c:forEach items="${AllUnits}" var="Units">
											<option value="${Units.getUnitId()}">
												<c:out value="${Units.getUnitName()}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="ui four column centered grid">
								<div class="column">
									<button class="ui blue button" type="submit">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Patient details modal end-->

		<!--  Search patient by name modal -->
		<div class="ui modal mini SearchPatientByName">
			<i class="close icon"></i>
			<div class="header">Search Patient By Name</div>
			<div class="ui grid centered">
				<div class="column">
					<div class="ui segment">
						<form class="ui form" method="post"
							action="<c:url value="/searchPatientController"/>">
							<div class="field">
								<label>Enter Patient's First Name</label> <input type="text"
									name="SearchPatientFirstName" placeholder="First Name"
									pattern="^[a-zA-Z]+$"
									title='first name must contain only letters' required>
							</div>

							<div class="field">
								<label>Enter Patient's Last Name</label> <input type="text"
									name="SearchPatientLastName" placeholder="Last Name"
									pattern="^[a-zA-Z]+$"
									title='last name must contain only letters' required>
							</div>

							<div class="ui three column centered grid">
								<div class="column">
									<button class="ui blue button" type="submit">Search
										Patient</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Search patient by name modal end -->

		<!--  Add new unit modal -->
		<div class="ui modal mini AddNewUnit">
			<i class="close icon"></i>
			<div class="header">Add New Unit</div>
			<div class="ui grid centered">
				<div class="column">
					<div class="ui segment">
						<form class="ui form" method="post"
							action="<c:url value="/addNewUnitController"/>">
							<div class="field">
								<label>Unit Name</label> <input type="text" name="UnitName"
									placeholder="Unit Name" required>
							</div>

							<div class="field">
								<label>No of Wards</label> <input type="text" name="NoOfWards"
									placeholder="Number of Wards" pattern='[1-9][0-9]*'
									title="Only numbers are allowed" required>
							</div>

							<div class="field">
								<label>Maximum Patients per Ward</label> <input type="text"
									name="MaxPatientsPerWard" placeholder="Maximum Patients"
									pattern='[1-9][0-9]*' title="Only numbers are allowed" required>
							</div>

							<div class="ui three column centered grid">
								<div class="column">
									<button class="ui blue button" type="submit">Add Unit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--  Add new unit modal end-->
	</div>

	<!-- Modal generate patient report -->
	<div class="ui modal mini GeneratePatientReport">
		<i class="close icon"></i>
		<div class="header">Generate Patient Report</div>
		<div class="ui grid centered">
			<div class="column">
				<div class="ui segment">
					<form class="ui form" method="post"
						action="<c:url value="/AllAdmittedPatientReportController"/>">
						<div class="ui three column centered grid">
							<div class="column">
								<button class="ui blue button" type="submit">All
									Admitted Patients</button>
							</div>
						</div>
					</form>
					<form class="ui form" method="post"
						action="<c:url value="/StillAdmittedPatientReportController"/>">
						<div class="ui three column centered grid">
							<div class="column">
								<button class="ui blue button" type="submit">Not yet Discharged Patients</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- modal end generate patient report -->
	
	<!--  Get success and error messages from servlets-->
	<div id="NoPatientRecord" class="displayControl">${NoSearchPatientResults}</div>
	<div id="Ward" class="displayControl">${NoWard}</div>
	<div id="AdmissionSuccess" class="displayControl">${AdmitPatientSuccess}</div>
	<div id="AdmissionFailure" class="displayControl">${AdmitPatientFailure}</div>
	<div id="UpdatePatientRecordSuccess" class="displayControl">${updateMessageSuccess}</div>
	<div id="UpdatePatientRecordFailure" class="displayControl">${updateMessageFailure}</div>
	<div id="DischargePatientFailure" class="displayControl">${DischargeFailure}</div>
	<div id="DischargePatientSuccess" class="displayControl">${DischargeSuccess}</div>
	<div id="AddUnitSuccess" class="displayControl">${AddUnitSuccess}</div>
	<div id="AddUnitFailure" class="displayControl">${AddUnitFailure}</div>
	<div id="DuplicateEntry" class="displayControl">${DuplicateRecord}</div>

	<!-- Modal to display Patient record not found error -->
	<div class="ui tiny modal NoPatientRecordFoundErrorModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">${NoSearchPatientResults}</div>
				<p style="font-size: 18px">The patient you try to find may be
					discharged or no record found for your search</p>
			</div>
		</div>
	</div>
	<!--  -->
	
	<!--  Modal to display duplicate entry message -->
	<div class="ui tiny modal DuplicateEntryModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">Duplicate Entry</div>
				<p style="font-size: 15px">${DuplicateRecord}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- Modal to display no available wards error -->
	<div class="ui tiny modal NoWardErrorModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">${NoWard}</div>
				<p style="font-size: 15px">All wards are filled</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- Modal to display admission success message -->
	<div class="ui mini modal AdmissionSuccessModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui positive message">
				<div class="header">${AdmitPatientSuccess}</div>
			</div>
		</div>
	</div>
	<!--  -->

		<!--  Modal to display update success message-->
	<div class="ui tiny modal UpdateSuccessModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui positive message">
				<p style="font-size: 15px">${updateMessageSuccess}</p>
			</div>
		</div>
	</div>
	<!--  Modal to display update success message-->

	<!--  Modal to display update failure message -->
	<div class="ui tiny modal UpdateFailureModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">An Error Occured</div>
				<p style="font-size: 15px">${updateMessageFailure}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- modal to display discharge successs message -->
	<div class="ui tiny modal DischargeSuccessModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui positive message">
				<p style="font-size: 15px">${DischargeSuccess}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- modal to display discharge failure modal -->
	<div class="ui tiny modal DischargeFailureModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">An Error Occured</div>
				<p style="font-size: 15px">${DischargeFailure}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- modal to display success of unit addition -->
	<div class="ui tiny modal AddUnitSuccessModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui positive message">
				<p style="font-size: 15px">${AddUnitSuccess}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!--  modal to display failure of addition of unit-->
	<div class="ui tiny modal AddUnitFailureModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">An Error Occured</div>
				<p style="font-size: 15px">${AddUnitFailure}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<!-- modal to display admission failure modal -->
	<div class="ui mini modal AdmissionFailureModal">
		<i class="close icon"></i>
		<div class="content">
			<div class="ui negative message">
				<div class="header">An Error Occured</div>
				<p style="font-size: 15px">${AdmitPatientFailure}</p>
			</div>
		</div>
	</div>
	<!--  -->

	<script>
	$('.ui.button.admitPatientButton').click(function() {
		$('.ui.modal.tiny.modelPatientDetails').modal('show');
		$('.ui.modal.tiny.modelPatientDetails').modal({
			backdrop : 'static',
			keyboard : false
		});
	});

	$('.ui.button.SearchPatientButton').click(function() {
		$('.ui.modal.mini.SearchPatientByName').modal('show');
		$('.ui.modal.mini.SearchPatientByName').modal({
			backdrop : 'static',
			keyboard : false
		});
	});

	$('.ui.button.addUnitButton').click(function() {
		$('.ui.modal.mini.AddNewUnit').modal('show');
		$('.ui.modal.mini.AddNewUnit').modal({
			backdrop : 'static',
			keyboard : false
		});
	});

	$('.ui.button.GenerateReportButton').click(function() {
		$('.ui.modal.mini.GeneratePatientReport').modal('show');
		$('.ui.modal.mini.GeneratePatientReport').modal({
			backdrop : 'static',
			keyboard : false
		});
	});

	var noPatientFound = $('#NoPatientRecord').html();
	var NoWardError = $('#Ward').html();
	var isAdmissionSuccess = $('#AdmissionSuccess').html();
	var isAdmissionFailure = $('#AdmissionFailure').html();
	var isUpdateSuccess = $('#UpdatePatientRecordSuccess').html();
	var isUpdateFailure = $('#UpdatePatientRecordFailure').html();
	var isDischargeSuccess = $('#DischargePatientSuccess').html();
	var isDischargeFailure = $('#DischargePatientFailure').html();
	var isUnitAddedSuccess = $('#AddUnitSuccess').html();
	var isUnitAddedFailure = $('#AddUnitFailure').html();
	var isDuplicateRecord = $('#DuplicateEntry').html();
	/* 
	 if (window.performance
	 && window.performance.navigation.type == window.performance.navigation.TYPE_BACK_FORWARD) {
	 var noPatientFound = null;
	 var NoWardError = null;
	 var admissionSuccess = null;
	 var isUpdateSuccess  null;
	 } */

	 
	 
	if (isUnitAddedSuccess === '') {
		$('.ui.tiny.modal.AddUnitSuccessModal').modal('hide');
	} else {
		$('.ui.tiny.modal.AddUnitSuccessModal').modal('show');
		$('.ui.tiny.modal.AddUnitSuccessModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isUnitAddedFailure === '') {
		$('.ui.tiny.modal.AddUnitFailureModal').modal('hide');
	} else {
		$('.ui.tiny.modal.AddUnitFailureModal').modal('show');
		$('.ui.tiny.modal.AddUnitFailureModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isDischargeSuccess === '') {
		$('.ui.tiny.modal.DischargeSuccessModal').modal('hide');
	} else {
		$('.ui.tiny.modal.DischargeSuccessModal').modal('show');
		$('.ui.tiny.modal.DischargeSuccessModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isDischargeFailure === '') {
		$('.ui.tiny.modal.DischargeFailureModal').modal('hide');
	} else {
		$('.ui.tiny.modal.DischargeFailureModal').modal('show');
		$('.ui.tiny.modal.DischargeFailureModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isUpdateSuccess === '') {
		$('.ui.tiny.modal.UpdateSuccessModal').modal('hide');
	} else {
		$('.ui.tiny.modal.UpdateSuccessModal').modal('show');
		$('.ui.tiny.modal.UpdateSuccessModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isUpdateFailure === '') {
		$('.ui.tiny.modal.UpdateFailureModal').modal('hide');
	} else {
		$('.ui.tiny.modal.UpdateFailureModal').modal('show');
		$('.ui.tiny.modal.UpdateFailureModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (noPatientFound === '') {
		$('.ui.tiny.modal.NoPatientRecordFoundErrorModal').modal('hide');
	} else {
		$('.ui.tiny.modal.NoPatientRecordFoundErrorModal').modal('show');
		$('.ui.tiny.modal.NoPatientRecordFoundErrorModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (NoWardError === '') {
		$('.ui.tiny.modal.NoWardErrorModal').modal('hide');
	} else {
		$('.ui.tiny.modal.NoWardErrorModal').modal('show');
		$('.ui.tiny.modal.NoWardErrorModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isAdmissionSuccess === '') {
		$('.ui.mini.modal.AdmissionSuccessModal').modal('hide');
	} else {
		$('.ui.mini.modal.AdmissionSuccessModal').modal('show');
		$('.ui.mini.modal.AdmissionSuccessModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}

	if (isAdmissionFailure === '') {
		$('.ui.mini.modal.AdmissionFailureModal').modal('hide');
	} else {
		$('.ui.mini.modal.AdmissionFailureModal').modal('show');
		$('.ui.mini.modal.AdmissionFailureModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}
	
	if (isDuplicateRecord === '') {
		$('.ui.tiny.modal.DuplicateEntryModal').modal('hide');
	} else {
		$('.ui.tiny.modal.DuplicateEntryModal').modal('show');
		$('.ui.tiny.modal.DuplicateEntryModal').modal({
			backdrop : 'static',
			keyboard : false
		});
	}
	</script>
	
	<!-- Footer start -->
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
	<!-- Footer end-->
</body>
</html>