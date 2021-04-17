package patientAndWard.Services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;
import patientAndWard.utils.HibernateUtil;

public class TestApp2 {
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		 ArrayList<Ward> wardArray = new ArrayList<Ward>(); 
//		 String hqlString = "SELECT W.wardId From Unit U LEFT JOIN U.wardList W Where U.unitId =: id AND ((W.maxPatientsAllowed - W.occupiedBeds) = 0)"; 
//		 Session session = sessionFactory.openSession();
//		 
//		 @SuppressWarnings("unchecked") 
//		 Query query = session.createQuery(hqlString);
//		 query.setParameter("id", unitId); 
//		 List<Object[]> wardResults = query.list();
//		 for (Object[] wardObjects : wardResults) { 
//			 int wardId = (int) wardObjects[0];
//			 Ward ward =session.get(Ward.class,wardId); 
//		 	wardArray.add(ward);
//		 
//		 }
//		 

//		 Session session = sessionFactory.openSession();
//		 Ward ward = new Ward(); ward.setWardNumber("04"); ward.setOccupiedBeds(5);
//		 ward.setMaxPatientsAllowed(20);
//		  
//		 Unit unit =session.get(Unit.class,1); unit.getWardList().add(ward);
//		 ward.setUnitWard(unit);
//		  
//		 session.beginTransaction(); session.persist(unit);
//		 session.getTransaction().commit();
////		 
		
//		List<PatientAdmitted> listOfAllPatientsAdmitted = null;
//		try (Session session = sessionFactory.openSession()) {
//			listOfAllPatientsAdmitted = session.createQuery("from PatientAdmitted").getResultList();
//			session.beginTransaction();
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		for(PatientAdmitted patientAdmitted : listOfAllPatientsAdmitted) {
//			System.out.println(patientAdmitted.getPatient_firstName());
//		}
		
		Session session = sessionFactory.openSession();
		String hqlString = "UPDATE Ward SET occupiedBeds=?1 WHERE wardId=?2";
		Query query = session.createQuery(hqlString);
		query.setParameter(1, 20);
		query.setParameter(2, 2);
		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();

	}

}
