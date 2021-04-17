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