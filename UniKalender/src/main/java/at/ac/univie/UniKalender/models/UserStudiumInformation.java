package at.ac.univie.UniKalender.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserStudiumInformation {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String fachgebietId;
	private String fachgebietName;
	private String studienrichtungId;
	private String studienrichtungName;
	public Long getId() {
		return userId;
	}
	public void setId(Long id) {
		this.userId = id;
	}
	public String getFachgebietId() {
		return fachgebietId;
	}
	public void setFachgebietId(String fachgebietId) {
		this.fachgebietId = fachgebietId;
	}
	public String getFachgebietName() {
		return fachgebietName;
	}
	public void setFachgebietName(String fachgebietName) {
		this.fachgebietName = fachgebietName;
	}
	public String getStudienrichtungId() {
		return studienrichtungId;
	}
	public void setStudienrichtungId(String studienrichtungId) {
		this.studienrichtungId = studienrichtungId;
	}
	public String getStudienrichtungName() {
		return studienrichtungName;
	}
	public void setStudienrichtungName(String studienrichtungName) {
		this.studienrichtungName = studienrichtungName;
	}
	
	
}
