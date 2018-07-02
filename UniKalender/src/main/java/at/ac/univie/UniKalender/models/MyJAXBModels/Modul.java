package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="module")
@XmlAccessorType(XmlAccessType.NONE)
public class Modul implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@XmlAttribute(name="id")
	private String id;
	/*
	@ElementCollection
	@CollectionTable(name="Category", joinColumns=@JoinColumn(name="id"))
	@Column(length=10000, name="category")
	*/
	@XmlElement(name="category")
	private String category;
	
	/*
	@ElementCollection
	@CollectionTable(name="Title", joinColumns=@JoinColumn(name="id"))
	@Column(length=10000, name="title")
	*/
	@Column(length=10000)
	@XmlElement(name="title")
	private String title;
	
	@Column(length=10000)
	@XmlElement(name="comment")
	private String comment;
	/*
	@ElementCollection
	@CollectionTable(name="Modul", joinColumns=@JoinColumn(name="id"))
	*/
	@Transient
	@XmlElement(name="module")
	private List<Modul> modul;
	
	@Transient
	@XmlElement(name="courses")
	private List<Courses> courses;
	
	@NotNull
	private String fachGebietId;
	
	
	public List<Courses> getCourses() {
		return courses;
	}
	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}
	public List<Modul> getModul() {
		return modul;
	}
	public void setModul(List<Modul> modul) {
		this.modul = modul;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFachGebietId() {
		return fachGebietId;
	}
	public void setFachGebietId(String fachGebietId) {
		this.fachGebietId = fachGebietId;
	}
	
	

}
