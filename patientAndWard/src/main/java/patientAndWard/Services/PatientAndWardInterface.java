package patientAndWard.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.boot.model.naming.ImplicitCollectionTableNameSource;

import com.sun.security.auth.NTDomainPrincipal;


import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;

public interface PatientAndWardInterface {
	public List<Unit> getAllUnits();
	
	public ArrayList<Ward> getWards(int unitId);
	
	public HashMap<String, String> admitPatient(int wardId, PatientAdmitted patientAdmitted);
	
	public PatientAdmitted searchPatientByNAme(String firstName,String lastName);
	
	public HashMap<String, String> updatePatientDetails(PatientAdmitted updatePatient);
	
	public HashMap<String, String> dischargePatient(int patientId);
	
	public void updateOccupiedBedsWhenDischarged(int wardId);
	
	public List<PatientAdmitted> getAllThePatients();
	
	public HashMap<String, String> addNewUnit(Unit unit,ArrayList<Ward> listOfWards);
	
	public boolean deletePatientRecord(int patientId);
	
	public void generateAllAdmittedPatientsReport();
	
	public List<PatientAdmitted> getAdmittedPatientListForGivenYearMonth(int year,int month);
	
	public void generateNotYetDischargedPatientsReport();
	
	public List<PatientAdmitted> getNotYetDischargedPatientListForGivenYearMonth(int year,int month);

}
