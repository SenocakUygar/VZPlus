package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class FachGebietModule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@XmlAttribute(name="id")
	private String id;
	@XmlAttribute(name="path")
	private String path;
	@XmlAttribute(name="when")
	private String semester;
	
	/*
	@ElementCollection
	@CollectionTable(name="List", joinColumns=@JoinColumn(name="id"))
	*/
	@Column(length=10000, name="title")
	@XmlElement(name="title")
	private String title;
	
	@NotNull
	private String richtungId;

	public String getId() {
		return id;
	}
	public String getRichtungId() {
		return richtungId;
	}
	public void setRichtungId(String richtungId) {
		this.richtungId = richtungId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
