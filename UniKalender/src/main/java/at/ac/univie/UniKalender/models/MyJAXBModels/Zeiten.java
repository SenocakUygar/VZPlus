package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "wwlong")
public class Zeiten {
	
	@XmlElement(name="wwevent")
	private List<Zeit> zeitList;

	public List<Zeit> getZeitList() {
		return zeitList;
	}

	public void setZeitList(List<Zeit> zeitList) {
		this.zeitList = zeitList;
	}
	
	

}
