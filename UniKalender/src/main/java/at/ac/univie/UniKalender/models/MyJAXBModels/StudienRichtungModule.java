package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="Studienrichtung")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudienRichtungModule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@XmlAttribute(name="id")
	private String id;
	
	/*
	@ElementCollection
	@CollectionTable(name="Title", joinColumns=@JoinColumn(name="id"))
	*/
	@Column(length=10000)
	@XmlElement(name="title")
	private String title;
	
	/*
	@ElementCollection
	@CollectionTable(name="Comment", joinColumns=@JoinColumn(name="id"))
	*/
	@Column(length=10000)
	@XmlElement(name="comment")
	private String comment;

	@Transient
	@XmlElement(name="module")
	private List<FachGebietModule> fachGebiet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<FachGebietModule> getFachGebiet() {
		return fachGebiet;
	}

	public void setFachGebiet(List<FachGebietModule> fachGebiet) {
		this.fachGebiet = fachGebiet;
	}
	
}
