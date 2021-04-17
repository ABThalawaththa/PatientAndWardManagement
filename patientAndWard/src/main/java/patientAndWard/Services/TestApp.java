package patientAndWard.Services;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;

import patientAndWard.utils.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		PatientAdmitted patient = new PatientAdmitted();
		patient.setPatient_firstName("kamal");
		patient.setPatient_lastName("perera");
		patient.setAddress("colombo");
		patient.setAge(34);
		patient.setContactNo("0773501552");
		patient.setAdmittedByFirstName("Wickrama");
		patient.setAdmittedByLastName("Perera");
		patient.setCauseOfAdmission("Dengue Fever");
		String dataString = "2020-04-28";
		Date date = Date.valueOf(dataString);
		patient.setDateOfAdmission(date);
		
		Ward ward = new Ward();
		ward.setWardNumber("01");
		ward.setOccupiedBeds(1);
		ward.setMaxPatientsAllowed(20);
		
		Unit unit = new Unit();
		unit.setUnitName("OPD");
		
		ward.getPatientList().add(patient);
		patient.setWardPatient(ward);
		
		unit.getWardList().add(ward);
		ward.setUnitWard(unit);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(unit);
		session.getTransaction().commit();
		
		PatientAdmitted patient2 =session.get(PatientAdmitted.class,1);
		Ward ward2 = patient2.getWardPatient();
		Unit unit2 = ward2.getUnitWard();
		
		
		
		System.out.println(patient2.getPatient_firstName());
		System.out.println(ward2.getWardNumber());
		System.out.println(unit2.getUnitName());
		
		session.close();
	}

}
