package patientAndWard.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import patientAndWard.entities.Unit;

import patientAndWard.entities.PatientAdmitted;

@Entity
@Table(name = "ward")
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wardId")
	private int wardId;
	
	@Column(name = "wardNo")
	private String wardNumber;
	
	@Column(name ="maxPatients")
	private int maxPatientsAllowed;
	
	@Column(name = "occupiedBeds")
	private int occupiedBeds;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "wardPatient")
	private List<PatientAdmitted> patientList = new ArrayList();
	
	@ManyToOne
	@JoinColumn(name="unit_Id")
	private Unit unitWard;

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	public int getMaxPatientsAllowed() {
		return maxPatientsAllowed;
	}

	public void setMaxPatientsAllowed(int maxPatientsAllowed) {
		this.maxPatientsAllowed = maxPatientsAllowed;
	}

	public int getOccupiedBeds() {
		return occupiedBeds;
	}

	public void setOccupiedBeds(int occupiedBeds) {
		this.occupiedBeds = occupiedBeds;
	}

	public List<PatientAdmitted> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<PatientAdmitted> patientList) {
		this.patientList = patientList;
	}

	public Unit getUnitWard() {
		return unitWard;
	}

	public void setUnitWard(Unit unitWard) {
		this.unitWard = unitWard;
	}
	

	

}
