package at.ac.univie.UniKalender.models.MyJAXBModels;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="structure")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudienRichtungStructure {
	
	@XmlElement(name="module")
	private List<StudienRichtungModule> modulList;

	public List<StudienRichtungModule> getModulList() {
		return modulList;
	}

	public void setModulList(List<StudienRichtungModule> modulList) {
		this.modulList = modulList;
	}
	

}
