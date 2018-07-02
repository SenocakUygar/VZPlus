package at.ac.univie.UniKalender.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="importExport")
public class ImportExport {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private Long importId;
	private String von;
	private String wem;
	private boolean verfuegbar;
	
	
	public Long getImportId() {
		return importId;
	}
	public void setImportId(Long importId) {
		this.importId = importId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVon() {
		return von;
	}
	public void setVon(String von) {
		this.von = von;
	}
	public String getWem() {
		return wem;
	}
	public void setWem(String wem) {
		this.wem = wem;
	}
	public boolean isVerfuegbar() {
		return verfuegbar;
	}
	public void setVerfuegbar(boolean verfuegbar) {
		this.verfuegbar = verfuegbar;
	}
	
	
}
