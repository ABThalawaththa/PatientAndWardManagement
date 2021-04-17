package patientAndWard.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import patientAndWard.entities.Ward;

@Entity
@Table(name = "unit")
public class Unit {
	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", wardList=" + wardList + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="unitId")
	private int unitId;
	
	@Column(name = "unitName")
	private String unitName;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "unitWard")
	private List<Ward> wardList = new ArrayList();

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<Ward> getWardList() {
		return wardList;
	}

	public void setWardList(List<Ward> wardList) {
		this.wardList = wardList;
	}

}
