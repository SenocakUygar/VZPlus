package at.ac.univie.UniKalender.models;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CalendarEvents {

	private String title;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss", timezone="CET")
	private Calendar start;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss", timezone="CET")
	private Calendar end;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String ort;
	
	
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Calendar getStart() {
		return start;
	}
	public void setStart(Calendar start) {
		this.start = start;
	}
	public Calendar getEnd() {
		return end;
	}
	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	
	
}
