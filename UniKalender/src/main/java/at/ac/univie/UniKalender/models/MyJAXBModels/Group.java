package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="\"GROUP\"")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "group")
public class Group implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@XmlAttribute(name="id")
	private String groupId;
	@Column(length=10000)
	@XmlElement(name="comment")
	private String comment;
	@Column(length=10000)
	@XmlElement(name="platform")
	private String platform;
	
	//TODO
	@Transient
	@XmlElementWrapper(name="lecturers")
	@XmlElement(name="lecturer")
	private Lecturer[] lectureList;
	
	@Transient
	@XmlElement(name="wwlong")
	private Zeiten zeiten;
	
	@NotNull
	private String courseId;
	
	
	
	/*
	private GregorianCalendar regisFrom;
	
	
	private GregorianCalendar to;
	*/
	
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Lecturer[] getLectureList() {
		return lectureList;
	}
	public void setLectureList(Lecturer[] lectureList) {
		this.lectureList = lectureList;
	}
	public Zeiten getZeiten() {
		return zeiten;
	}
	public void setZeiten(Zeiten zeiten) {
		this.zeiten = zeiten;
	}
	
	/*
	public GregorianCalendar getRegisFrom() {
		return regisFrom;
	}
	public void setRegisFrom(GregorianCalendar regisFrom) {
		this.regisFrom = regisFrom;
	}
	public GregorianCalendar getTo() {
		return to;
	}
	public void setTo(GregorianCalendar to) {
		this.to = to;
	}
	*/
	
}
