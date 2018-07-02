package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "wwevent")
public class Zeit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="beginZeit")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlAttribute(name="begin")
	private Calendar begin;
	
	@Column(name="endZeit")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlAttribute(name="end")
	private Calendar end;
	
	@XmlAttribute(name="room")
	private String room;
	
	@XmlAttribute(name="town")
	private String town;
	
	@XmlAttribute(name="zip")
	private String zip;
	
	@Column(length=10000)
	@XmlAttribute(name="address")
	private String address;
	
	@Transient
	@XmlElement(name="location")
	private Location location;
	
	@NotNull
	//@Column(unique=true)
	private String groupId;

	@XmlElement(name="title")
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Calendar getBegin() {
		return begin;
	}
	public void setBegin(Calendar begin) {
		this.begin = begin;
	}
	public Calendar getEnd() {
		return end;
	}
	public void setEnd(Calendar end) {
		this.end = end;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
