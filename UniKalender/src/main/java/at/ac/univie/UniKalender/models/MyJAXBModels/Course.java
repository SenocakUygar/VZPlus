package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "course")
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@XmlAttribute(name="id")
	private String courseId;
	
	@XmlElement(name="type")
	private String type;
	
	//TODO List<String>
	@XmlElement(name="longname")
	private String longname;
	
	@XmlElement(name="ects")
	private String ects;
	
	@Transient
	@XmlElement(name="groups")
	private Groups groups;
	
	@NotNull
	private String modulId;
	
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public String getModulId() {
		return modulId;
	}
	public void setModulId(String modulId) {
		this.modulId = modulId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLongname() {
		return longname;
	}
	public void setLongname(String longname) {
		this.longname = longname;
	}
	public String getEcts() {
		return ects;
	}
	public void setEcts(String ects) {
		this.ects = ects;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	
	
}
