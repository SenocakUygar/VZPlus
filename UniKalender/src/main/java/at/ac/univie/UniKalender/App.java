package at.ac.univie.UniKalender;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.xml.sax.SAXException;

import at.ac.univie.UniKalender.logik.MyXMLReader;
import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.models.MyJAXBModels.Course;
import at.ac.univie.UniKalender.models.MyJAXBModels.Courses;
import at.ac.univie.UniKalender.models.MyJAXBModels.FachGebietModule;
import at.ac.univie.UniKalender.models.MyJAXBModels.Group;
import at.ac.univie.UniKalender.models.MyJAXBModels.Groups;
import at.ac.univie.UniKalender.models.MyJAXBModels.Modul;
import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungModule;
import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungStructure;
import at.ac.univie.UniKalender.models.MyJAXBModels.Zeit;
import at.ac.univie.UniKalender.repositorys.CourseRepository;
import at.ac.univie.UniKalender.repositorys.FachGebietRepository;
import at.ac.univie.UniKalender.repositorys.GroupRepository;
import at.ac.univie.UniKalender.repositorys.ModulRepository;
import at.ac.univie.UniKalender.repositorys.StudienRichtungRepository;
import at.ac.univie.UniKalender.repositorys.UserRepository;
import at.ac.univie.UniKalender.repositorys.ZeitRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by uygarsenocak on 01.04.16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvcSecurity
public class App implements CommandLineRunner 
{
	private static final Logger log = LoggerFactory.getLogger(App.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	StudienRichtungRepository studienRichtungRepository;
	@Autowired
	FachGebietRepository fachGebietRepository;
	@Autowired
	ModulRepository modulRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ZeitRepository zeitRepository;
	private MyXMLReader reader = new MyXMLReader("2016S");
	
	
	public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
	
	@Override
	public void run(String... arg0) throws Exception {
		
		/*
		//String username, String email, String password
		String password = "1234";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		userRepository.save(new User("Uygar", "uygare@gmail.com", hashedPassword));
			
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			
		
		
		//TODO die Datan wird in die Database hinzufügt
		
		*/
		/*
			reader.liesStudienRichtung();
			
			StudienRichtungStructure studienRichtungStructure = reader.getStudienRichtungStructure();
			for (StudienRichtungModule studienRichtungModule: studienRichtungStructure.getModulList())
			{
				System.out.println(studienRichtungModule.getId());
				if (studienRichtungModule != null && studienRichtungModule.getFachGebiet() != null)
				{
					
					for (FachGebietModule fachGebietModule : studienRichtungModule.getFachGebiet())
					{
						fachGebietModule.setRichtungId(studienRichtungModule.getId());
						fachGebietRepository.save(fachGebietModule);
						Modul modul = reader.liesModulName(fachGebietModule.getId());
						liesModule(modul, fachGebietModule);
					}
						
					studienRichtungRepository.save(studienRichtungModule);
				}
			}
		*/
	}
	
	private void liesModule(Modul modul, FachGebietModule fachGebietModule ) throws MalformedURLException, ParserConfigurationException, SAXException, IOException
	{
		
		modul.setFachGebietId(fachGebietModule.getId());
		modulRepository.save(modul);
		if (modul.getCourses() != null)
		for (Courses courses : modul.getCourses())
		{
			if (courses.getCourse() != null)
			for(Course course : courses.getCourse())
			{
				course.setModulId(modul.getId());
				courseRepository.save(course);
				Groups groups = reader.liesGroups(course.getCourseId());
				if (groups != null && groups.getGroupList() != null)
				for(Group group : groups.getGroupList())
				{
					group.setCourseId(course.getCourseId());
					groupRepository.save(group);
					if (group.getZeiten() != null && group.getZeiten().getZeitList() != null)
					{
						for (Zeit zeit : group.getZeiten().getZeitList())
						{
							zeit.setGroupId(group.getGroupId());
							zeit.setTitle(modul.getTitle());
							zeitRepository.save(zeit);
						}
					}
				}
				
			}
		}
		if (modul.getModul() != null)
		{
			for (Modul innereModul : modul.getModul())
				liesModule(innereModul, fachGebietModule);
		}
	}
}
