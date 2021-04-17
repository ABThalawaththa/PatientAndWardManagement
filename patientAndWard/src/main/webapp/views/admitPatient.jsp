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
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>

<body>
	<!-- Modal to select ward -->
	<div class="ui modal tiny WardSelection">
		<i class="close icon"></i>
		<div class="header">Ward Selection</div>
		<div class="ui grid centered">
			<div class="column">
				<div class="ui segment">
					<form class="ui form" method="post"
						action="<c:url value="/patientInputController2"/>">
						<div class="field">
							<label>Select Ward</label> <select class="ui fluid dropdown"
								name="wardSelected">
								<c:forEach items="${wardList}" var="Wards">
									<option value="${Wards.getWardId()}">
										<c:out value="${Wards.getWardNumber()}"></c:out>
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="ui three column centered grid">
							<div class="column">
								<button class="ui red button" type="submit">Admit Patient</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal end -->
	
		<script>
			$('.ui.modal.tiny.WardSelection').modal('show');
			$('.ui.modal.tiny.WardSelection').modal({backdrop: 'static', keyboard: false});
		</script>
</body>
</html>