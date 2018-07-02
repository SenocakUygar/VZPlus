package at.ac.univie.UniKalender.logik;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import at.ac.univie.UniKalender.models.MyJAXBModels.Course;
import at.ac.univie.UniKalender.models.MyJAXBModels.Courses;
import at.ac.univie.UniKalender.models.MyJAXBModels.FachGebietModule;
import at.ac.univie.UniKalender.models.MyJAXBModels.Group;
import at.ac.univie.UniKalender.models.MyJAXBModels.Groups;
import at.ac.univie.UniKalender.models.MyJAXBModels.Modul;
import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungModule;
import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungStructure;
import at.ac.univie.UniKalender.models.MyJAXBModels.Zeit;


public class MyXMLReader {
	private String hauptSeiteUrl;
	private StudienRichtungStructure studienRichtungStructure = null;
	private String semester;
	
	public MyXMLReader(){}
	
	public MyXMLReader(String semester)
	{
		this.semester = semester;
		hauptSeiteUrl = "https://m1-ufind.univie.ac.at/courses/browse/"+semester+"/";
	}
	
	
	//Erste Aufgabe: lies Studien Richtungen
	public void liesStudienRichtung()
	{
		JAXBContext jc;
		
		try {
			jc = JAXBContext.newInstance(StudienRichtungStructure.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	        URL url = new URL(hauptSeiteUrl);
	        
	       
	        studienRichtungStructure = (StudienRichtungStructure) unmarshaller.unmarshal(url);
	        
	        
	        /*
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        marshaller.marshal(studienRichtungStructure, System.out);
	        */
	        
		} catch (JAXBException e) {
			System.out.println("Fehler -> MyXMLReader -> leseStudienRictung 1");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Fehler -> MyXMLReader -> leseStudienRictung 2");
			e.printStackTrace();
		}
		
	}
	
	public Modul liesModulName(String id) throws ParserConfigurationException, MalformedURLException, SAXException, IOException
	{
		String url = "https://m1-ufind.univie.ac.at/courses/browse/" + id;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = factory.newDocumentBuilder();
			
		Document doc = builder.parse(new URL(url).openStream());
		
		NodeList structure = doc.getElementsByTagName("structure");
		Element structureElement = (Element) structure.item(0);
		NodeList modul_level1 = structureElement.getElementsByTagName("module");
		Element modul_level1Element = (Element) modul_level1.item(0);
		//NodeList modul_level2 = modul_level1Element.getElementsByTagName("module");
		//Element modul_level2Element = (Element) modul_level2.item(0);
		//System.out.println(modul_level1);
		
		JAXBContext jc;
		Modul modul = null;
		try {
			jc = JAXBContext.newInstance(Modul.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	        //URL url = new URL(hauptSeiteUrl);
	        
	       
	        modul = (Modul) unmarshaller.unmarshal(modul_level1Element);
	        
	        
	        /*
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        marshaller.marshal(modul, System.out);
	        */
	        
		} catch (JAXBException e) {
			System.out.println("Fehler -> MyXMLReader -> leseStudienRictung 1");
			e.printStackTrace();
		}
		return modul;
	}
	
	public Groups liesGroups(String id) throws MalformedURLException, SAXException, IOException, ParserConfigurationException
	{
		String url = "https://m1-ufind.univie.ac.at/courses/" + id;
		//System.out.println(url);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = null;
		Groups groups = null;
		try{	
			doc = builder.parse(new URL(url).openStream());
			NodeList course = doc.getElementsByTagName("course");
			Element courseElement = (Element) course.item(0);
			NodeList course2 = courseElement.getElementsByTagName("groups");
			Element courseElement2 = (Element) course2.item(0);
			//System.out.println("Doc : " + courseElement2.getTagName());
			
			JAXBContext jc;
			
			try {
				jc = JAXBContext.newInstance(Groups.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
		        //URL url = new URL(hauptSeiteUrl);
		        
		       
				groups = (Groups) unmarshaller.unmarshal(courseElement2);
		        
		        /*
		        Marshaller marshaller = jc.createMarshaller();
		        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);        
		        marshaller.marshal(groups, System.out);
		        */
		        
			} catch (JAXBException e) {
				System.out.println("Fehler -> MyXMLReader -> leseStudienRictung 1");
				e.printStackTrace();
			}
		}catch(Exception e)
		{
			System.out.println("Url kann nicht gelesen werden : "+ url);
		}
		
		return groups;
	}
	
	public StudienRichtungStructure getStudienRichtungStructure() {
		return studienRichtungStructure;
	}

	public void setStudienRichtungStructure(StudienRichtungStructure studienRichtungStructure) {
		this.studienRichtungStructure = studienRichtungStructure;
	}

	public static void main(String[] args) {
		MyXMLReader reader = new MyXMLReader("2016S");
		
		reader.liesStudienRichtung();
		StudienRichtungStructure studienRichtungStructure = reader.getStudienRichtungStructure();
		try {
			//for (StudienRichtungModule modul : studienRichtungStructure.getModulList())
			{
				
				//System.out.println(modul.getTitle());
				//System.out.println(modul.getFachGebiet().get(0).getTitle());
				//if (modul.getFachGebiet() != null)
				//for (FachGebietModule fachGebietModule : modul.getFachGebiet())
				{
					//reader.liesModulName("https://m1-ufind.univie.ac.at/courses/browse/155643");
					Modul modul = reader.liesModulName("155835");
					for (Modul modul2 : modul.getModul())
					{
						if (modul2.getModul() != null)
						for (Modul modul3 : modul2.getModul())
						{
							for (Courses courses : modul3.getCourses())
							{
								if (courses.getCourse() != null)
								for (Course course : courses.getCourse())
								{
									System.out.println(course.getLongname());
								}
							}
						}
					}
					//if (myModul.getId() == null)
						//System.out.println(fachGebietModule.getId());
					//else if (myModul.getId().equals("155064"))
						//System.out.println("Hİerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr1");
					/*if (myModul.getModul() != null)
					{
						List<Modul> myModulList = myModul.getModul();
						for (Modul modul2 : myModulList)
						{
							System.out.println(modul2.getTitle());
						if (modul2.getId().equals("155064"))
							System.out.println("Hİerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
						}
					}*/
					//System.out.println(myModul.getId());
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

			//reader.liesGroups("050022");
		/*
			Groups groups = null;
			try {
				groups = reader.liesGroups("050022");
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Group group : groups.getGroupList())
			{
				if (group.getZeiten() != null && group.getZeiten().getZeitList() != null)
				{
					for (Zeit zeit : group.getZeiten().getZeitList())
					{
						System.out.println(zeit.getBegin());
					}
				}
				else
				{
					
					
					System.out.println(group.getGroupId() + " group.getZeiten() keine Zeit");
					//else if (group.getZeiten().getZeitList() != null)
						//System.out.println(group.getGroupId() + " group.getZeiten().getZeitList() keine Zeit");
						
	
				}
			}
			*/
		
	}

}
