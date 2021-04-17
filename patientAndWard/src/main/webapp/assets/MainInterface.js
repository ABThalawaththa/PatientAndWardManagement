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
			$('.ui.mini.modal.AddmissionSuccessModal').modal('hide');
		} else {
			$('.ui.mini.modal.AddmissionSuccessModal').modal('show');
			$('.ui.mini.modal.AddmissionSuccessModal').modal({
				backdrop : 'static',
				keyboard : false
			});
		}

		if (isAdmissionFailure === '') {
			$('.ui.mini.modal.AddmissionFailureModal').modal('hide');
		} else {
			$('.ui.mini.modal.AddmissionFailureModal').modal('show');
			$('.ui.mini.modal.AddmissionFailureModal').modal({
				backdrop : 'static',
				keyboard : false
			});
		}