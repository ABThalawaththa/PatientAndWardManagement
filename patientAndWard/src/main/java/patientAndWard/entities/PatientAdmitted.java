package patientAndWard.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.boot.jaxb.hbm.spi.SimpleValueTypeInfo;

import patientAndWard.entities.Ward;

@Entity
@Table(name = "patientAdmitted")
public class PatientAdmitted {
	@Id
	@Column(name = "patienId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@Column(name = "firstName",nullable = false)
	private String patient_firstName;
	
	@Column(name = "lastName",nullable = false)
	private String patient_lastName;
	
	@Column(name = "age",nullable = false)
	private int age;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@Column(name = "admittedByFirstName")
	private String admittedByFirstName;
	
	@Column(name = "admittedByLastName")
	private String admittedByLastName;
	
	@Column(name = "causeOfAdmission",nullable = false)
	private String causeOfAdmission;
	
	@Column(name = "dateAdmitted",nullable = false)
	private Date dateOfAdmission;
	
	@Column(name="dateDischarged")
	private Date dateDischarged;
	
	

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Date getDateDischarged() {
		return dateDischarged;
	}

	public void setDateDischarged(Date dateDischarged) {
		this.dateDischarged = dateDischarged;
	}

	@Column(name = "contactNo")
	private String contactNo;
	
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@ManyToOne
	@JoinColumn(name="ward_Id")
	private Ward wardPatient;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatient_firstName() {
		return patient_firstName;
	}

	public void setPatient_firstName(String patient_firstName) {
		this.patient_firstName = patient_firstName;
	}

	

	public String getPatient_lastName() {
		return patient_lastName;
	}

	public void setPatient_lastName(String patient_lastName) {
		this.patient_lastName = patient_lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdmittedByFirstName() {
		return admittedByFirstName;
	}

	public void setAdmittedByFirstName(String admittedByFirstName) {
		this.admittedByFirstName = admittedByFirstName;
	}

	public String getAdmittedByLastName() {
		return admittedByLastName;
	}

	public void setAdmittedByLastName(String admittedByLastName) {
		this.admittedByLastName = admittedByLastName;
	}

	public String getCauseOfAdmission() {
		return causeOfAdmission;
	}

	public void setCauseOfAdmission(String causeOfAdmission) {
		this.causeOfAdmission = causeOfAdmission;
	}

	

	public Ward getWardPatient() {
		return wardPatient;
	}

	public void setWardPatient(Ward wardPatient) {
		this.wardPatient = wardPatient;
	}

	

}
