package patientAndWard.Services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.Table;
import javax.validation.ReportAsSingleViolation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.query.Query;
import org.hibernate.sql.Delete;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationAttribute;

import com.itextpdf.testutils.ITextTest;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import net.bytebuddy.implementation.Implementation.Context.ExtractableView;
import patientAndWard.Servlets.AllAdmittedPatientReportController;
import patientAndWard.Servlets.DischargePatientController;
import patientAndWard.Servlets.StillAdmittedPatientReportController;
import patientAndWard.Servlets.addNewUnitController;
import patientAndWard.Servlets.patientInputController;
import patientAndWard.Servlets.searchPatientController;
import patientAndWard.Servlets.updatePatientDetailsController;
import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;
import patientAndWard.utils.HibernateUtil;


public class PatientAndWardImpl implements PatientAndWardInterface {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	/* get all the units */
	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getAllUnits() {
		List<Unit> units = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			units = session.createQuery("from Unit").getResultList();
			transaction.commit();

		} catch (Exception e) {
			units = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return units;
	}

	/* get all the wards of specific unit by unit id */
	@Override
	public ArrayList<Ward> getWards(int unitId) {
		ArrayList<Ward> wardArray = new ArrayList<Ward>();
		String hqlString = "SELECT W.wardId From Unit U LEFT JOIN U.wardList W Where U.unitId =: id AND ((W.maxPatientsAllowed - W.occupiedBeds) > 0)";
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<Integer> query = session.createQuery(hqlString);
		query.setParameter("id", unitId);
		List<Integer> wardResults = query.list();
		if (wardResults.isEmpty()) {
			return wardArray;
		} else {
			for (Integer wardNumber : wardResults) {
				Ward ward = session.get(Ward.class, wardNumber);
				wardArray.add(ward);
			}
			return wardArray;
		}

	}

	/* Insert patient details */ 
	@Override
	public HashMap<String, String> admitPatient(int wardId, PatientAdmitted patientAdmitted) {
		HashMap<String, String> message = new HashMap<String, String>();
		try (Session session = sessionFactory.openSession()) {
			Ward ward = session.get(Ward.class, wardId);
			ward.getPatientList().add(patientAdmitted);
			patientAdmitted.setWardPatient(ward);

			//Increment occupied bed count in the ward
			int occupiedBeds = ward.getOccupiedBeds();
			occupiedBeds = occupiedBeds + 1;

			String hQlString = "UPDATE Ward set occupiedBeds=:newOccupiedBeds WHERE wardId=:WARDID";
			Query query = session.createQuery(hQlString);
			query.setParameter("newOccupiedBeds", occupiedBeds);
			query.setParameter("WARDID", ward.getWardId());

			session.beginTransaction();
			int executeUpdate = query.executeUpdate();

			if (executeUpdate > 0) {
				session.persist(patientAdmitted);
				session.getTransaction().commit();
				message.put("message", "Patient is successfully admitted");
			}

		} catch (Exception e) {
			message.put("message", e.getMessage());
		}
		return message;

	}

	/*
	 * search Still admitted patients' details by using first name and last name
	 */	
	@Override
	public PatientAdmitted searchPatientByNAme(String firstName, String lastName) {
		PatientAdmitted patientReceived = null;
		try (Session session = sessionFactory.openSession()) {
			String hQLString = "From PatientAdmitted Where patient_firstName=?1 AND patient_lastName=?2 AND dateDischarged IS NULL";
			Query<PatientAdmitted> query = session.createQuery(hQLString, PatientAdmitted.class);
			query.setParameter(1, firstName);
			query.setParameter(2, lastName);
			patientReceived = query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientReceived;
	}

	/* Update patient details */
	@SuppressWarnings("finally")
	@Override
	public HashMap<String, String> updatePatientDetails(PatientAdmitted updatePatient) {
		HashMap<String, String> messages = new HashMap<String, String>();
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			String hqlString = "UPDATE PatientAdmitted SET patient_firstName=?1,patient_lastName=?2,age=?3,contactNo=?4,address=?5 WHERE patientId=?6";
			Query query = session.createQuery(hqlString);
			query.setParameter(1, updatePatient.getPatient_firstName());
			query.setParameter(2, updatePatient.getPatient_lastName());
			query.setParameter(3, updatePatient.getAge());
			query.setParameter(4, updatePatient.getContactNo());
			query.setParameter(5, updatePatient.getAddress());
			query.setParameter(6, updatePatient.getPatientId());
			transaction = session.beginTransaction();
			int result = query.executeUpdate();
			if (result > 0) {
				messages.put("message", "Successfully Updated");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				messages.put("message", e.getMessage());
				transaction.rollback();
			}
		} finally {
			return messages;
		}

	}

	/* Discharge patient */
	@Override
	public HashMap<String, String> dischargePatient(int patientId) {
		HashMap<String, String> message = new HashMap<String, String>();
		try (Session session = sessionFactory.openSession()) {
			PatientAdmitted patientAdmitted = session.get(PatientAdmitted.class, patientId);
			Ward ward = patientAdmitted.getWardPatient();
			int wardId = ward.getWardId();

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			String hqlsetDischargeDate = "UPDATE PatientAdmitted SET dateDischarged=?1 WHERE patientId=?2";
			Query queryUpdateDischargedDateQuery = session.createQuery(hqlsetDischargeDate);
			queryUpdateDischargedDateQuery.setParameter(1, date);
			queryUpdateDischargedDateQuery.setParameter(2, patientId);
			session.beginTransaction();
			int flag = queryUpdateDischargedDateQuery.executeUpdate();
			if (flag > 0) {
				session.getTransaction().commit();
				updateOccupiedBedsWhenDischarged(wardId);
				message.put("message", "Patient is successfully discharged");
			}

		} catch (Exception e) {
			message.put("message", e.getMessage());
		}
		return message;

	}

	/* update occupied bed count when patient is discharged */
	@Override
	public void updateOccupiedBedsWhenDischarged(int wardId) {
		try (Session session = sessionFactory.openSession()) {
			Ward ward = session.get(Ward.class, wardId);
			int occupiedBeds = ward.getOccupiedBeds();
			occupiedBeds = occupiedBeds - 1;
			String hqlDecrementOccupiedBedString = "UPDATE Ward SET occupiedBeds=?1 WHERE wardId=?2";
			Query queryDecrementOccupiedBedsQuery = session.createQuery(hqlDecrementOccupiedBedString);
			queryDecrementOccupiedBedsQuery.setParameter(1, occupiedBeds);
			queryDecrementOccupiedBedsQuery.setParameter(2, wardId);
			session.beginTransaction();
			queryDecrementOccupiedBedsQuery.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* get List of all the patients */
	@Override
	public List<PatientAdmitted> getAllThePatients() {
		List<PatientAdmitted> listOfAllPatientsAdmitted = null;
		try (Session session = sessionFactory.openSession()) {
			listOfAllPatientsAdmitted = session.createQuery("from PatientAdmitted").getResultList();
			session.beginTransaction();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfAllPatientsAdmitted;
	}
	
	
	/* add new unit to the system */
	@Override
	public HashMap<String, String> addNewUnit(Unit unit, ArrayList<Ward> listOfWards) {
		HashMap<String, String> message = new HashMap<String, String>();
		try (Session session = sessionFactory.openSession()) {
			for (Ward ward : listOfWards) {
				unit.getWardList().add(ward);
				ward.setUnitWard(unit);
			}
			session.beginTransaction();
			session.persist(unit);
			session.getTransaction().commit();
			message.put("message", "Unit is successfully added");

		} catch (Exception e) {
			message.put("message", e.getMessage());
		}
		return message;

	}

	/*
	 * Delete specific patient's record
	 */	
	@Override
	public boolean deletePatientRecord(int patientId) {
		try (Session session = sessionFactory.openSession()) {
			PatientAdmitted patientAdmitted = session.get(PatientAdmitted.class, patientId);
			Date dischargeDate = patientAdmitted.getDateDischarged();
			if (dischargeDate == null) {
				return false;
			} else {
				String deleteHQL = "DELETE FROM PatientAdmitted WHERE patientId=?1";
				Query query = session.createQuery(deleteHQL);
				query.setParameter(1, patientId);
				session.beginTransaction();
				query.executeUpdate();
				session.getTransaction().commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/* Get List of patients admitted according to the year and month admitted */
	@Override
	public List<PatientAdmitted> getAdmittedPatientListForGivenYearMonth(int year, int month) {
		List<PatientAdmitted> patientList = null;
		try (Session session = sessionFactory.openSession()) {
			String getPatientListByYearAdmitted = "FROM PatientAdmitted p WHERE year(p.dateOfAdmission)=?1 and month(p.dateOfAdmission)=?2";
			Query<PatientAdmitted> queryPatient = session.createQuery(getPatientListByYearAdmitted,
					PatientAdmitted.class);
			queryPatient.setParameter(1, year);
			queryPatient.setParameter(2, month);
			patientList = queryPatient.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientList;
	}

	/*
	 * Generate report of all the patients admitted sorted according to year and
	 * month admitted
	 */
	@Override
	public void generateAllAdmittedPatientsReport() {
		Document document = new Document();

		List<PatientAdmitted> allPatientsAdmitted = getAllThePatients();
		Set<Integer> yearsHashSet = new HashSet<Integer>();
		Set<Integer> monthsHashSet = new HashSet<Integer>();
		for (PatientAdmitted patientAdmitted : allPatientsAdmitted) {
			String dateAdmitted = patientAdmitted.getDateOfAdmission().toString();
			String[] partStrings = dateAdmitted.split("-");
			String year = partStrings[0];
			String month = partStrings[1];
			yearsHashSet.add(Integer.parseInt(year));
			monthsHashSet.add(Integer.parseInt(month));
		}

		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\Asiyaa\\Downloads\\allAdmittedPatients.pdf"));
			document.open();
			Paragraph title = new Paragraph("All Admitted Patients",FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph(new java.util.Date().toString()));
			document.add(new Paragraph(" "));
			

			for (int year : yearsHashSet) {
				for (int month : monthsHashSet) {
					document.add(new Paragraph(Integer.toString(year) + " - " + Integer.toString(month)));
					
					List<PatientAdmitted> list = getAdmittedPatientListForGivenYearMonth(year, month);
					if (list.isEmpty()) {
						document.add(new Paragraph("No Records to display"));
						document.add(new Paragraph(" "));
					} else {
						PdfPTable pdfPTable = new PdfPTable(10);
						pdfPTable.setWidthPercentage(100);
						pdfPTable.setSpacingBefore(10f);
						pdfPTable.addCell(new PdfPCell(new Paragraph("First Name",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Last Name",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Age",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Contact Number",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Address",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Unit",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Ward",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Cause Of Admission",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Admitted Date",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Discharged Date",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));

						for (PatientAdmitted patient : list) {
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getPatient_firstName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getPatient_lastName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(Integer.toString(patient.getAge()),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getContactNo(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getAddress(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getWardPatient().getUnitWard().getUnitName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getWardPatient().getWardNumber(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getCauseOfAdmission(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getDateOfAdmission().toString(),FontFactory.getFont(FontFactory.COURIER,11))));

							if (patient.getDateDischarged() == null) {
								pdfPTable.addCell(new PdfPCell(new Paragraph("-")));

							} else {
								pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getDateDischarged().toString(),FontFactory.getFont(FontFactory.COURIER,11))));

							}

						}
						document.add(pdfPTable);
						document.add(new Paragraph(" "));
					}

				}
			}

			document.close();
			pdfWriter.close();

		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	/*
	 * Generate report of all the patients not yet discharged sorted according to year and
	 * month addmitted
	 */
	@Override
	public List<PatientAdmitted> getNotYetDischargedPatientListForGivenYearMonth(int year, int month) {
		List<PatientAdmitted> patientList = null;
		try (Session session = sessionFactory.openSession()) {
			String getPatientListByYearAdmitted = "FROM PatientAdmitted p WHERE year(p.dateOfAdmission)=?1 and month(p.dateOfAdmission)=?2 and p.dateDischarged IS NULL";
			Query<PatientAdmitted> queryPatient = session.createQuery(getPatientListByYearAdmitted,
					PatientAdmitted.class);
			queryPatient.setParameter(1, year);
			queryPatient.setParameter(2, month);
			patientList = queryPatient.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientList;
	}

	@Override
	public void generateNotYetDischargedPatientsReport() {
		Document document = new Document();

		List<PatientAdmitted> allPatientsAdmitted = getAllThePatients();
		Set<Integer> yearsHashSet = new HashSet<Integer>();
		Set<Integer> monthsHashSet = new HashSet<Integer>();
		for (PatientAdmitted patientAdmitted : allPatientsAdmitted) {
			String dateAdmitted = patientAdmitted.getDateOfAdmission().toString();
			String[] partStrings = dateAdmitted.split("-");
			String year = partStrings[0];
			String month = partStrings[1];
			yearsHashSet.add(Integer.parseInt(year));
			monthsHashSet.add(Integer.parseInt(month));
		}

		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\Asiyaa\\Downloads\\NotYetDischargedPatients.pdf"));
			document.open();
			Paragraph title = new Paragraph("Not Yet Discharged Patients",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph(new java.util.Date().toString()));
			document.add(new Paragraph(" "));

			for (int year : yearsHashSet) {
				for (int month : monthsHashSet) {
					document.add(new Paragraph(Integer.toString(year) + " - " + Integer.toString(month)));

					List<PatientAdmitted> list = getNotYetDischargedPatientListForGivenYearMonth(year, month);
					if (list.isEmpty()) {
						document.add(new Paragraph("No Records to display"));
						document.add(new Paragraph(" "));
					} else {
						PdfPTable pdfPTable = new PdfPTable(10);
						pdfPTable.setWidthPercentage(100);
						pdfPTable.setSpacingBefore(10f);

						pdfPTable.addCell(new PdfPCell(new Paragraph("First Name",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Last Name",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Age",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Contact Number",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Address",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Unit",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Ward",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Cause Of Admission",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Admitted Date",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));
						pdfPTable.addCell(new PdfPCell(new Paragraph("Discharged Date",FontFactory.getFont(FontFactory.COURIER_BOLD,12))));

						for (PatientAdmitted patient : list) {
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getPatient_firstName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getPatient_lastName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(Integer.toString(patient.getAge()),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getContactNo(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getAddress(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getWardPatient().getUnitWard().getUnitName(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getWardPatient().getWardNumber(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getCauseOfAdmission(),FontFactory.getFont(FontFactory.COURIER,11))));
							pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getDateOfAdmission().toString(),FontFactory.getFont(FontFactory.COURIER,11))));
							if (patient.getDateDischarged() == null) {
								pdfPTable.addCell(new PdfPCell(new Paragraph("-")));

							} else {
								pdfPTable.addCell(new PdfPCell(new Paragraph(patient.getDateDischarged().toString(),FontFactory.getFont(FontFactory.COURIER,11))));

							}


						}
						document.add(pdfPTable);
						document.add(new Paragraph(" "));
					}

				}
			}

			document.close();
			pdfWriter.close();

		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	

}
