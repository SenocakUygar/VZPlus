package at.ac.univie.UniKalender.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.models.LehrfachAnmelden;
import at.ac.univie.UniKalender.models.MyJAXBModels.Course;
import at.ac.univie.UniKalender.models.MyJAXBModels.Group;
import at.ac.univie.UniKalender.models.MyJAXBModels.Groups;
import at.ac.univie.UniKalender.models.MyJAXBModels.Zeit;
import at.ac.univie.UniKalender.repositorys.CourseRepository;
import at.ac.univie.UniKalender.repositorys.GroupRepository;
import at.ac.univie.UniKalender.repositorys.LehrfachAnmeldenRepository;
import at.ac.univie.UniKalender.repositorys.ZeitRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;


@Controller
public class LehrfachController {
	
	private CustomUserDetails userDetails;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private ZeitRepository zeitRepository;
	@Autowired
	private LehrfachAnmeldenRepository anmeldungRepository;
	
	//TODO buna gereş yoı 
	private	List<String> groupIdList = new ArrayList<String>();
	
	@RequestMapping(value="/lehrfach", method=RequestMethod.GET)
	public ModelAndView lehrfach(@RequestParam(value="modulId") String modulId)
	{
		ModelAndView mav = new ModelAndView();
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		mav.addObject("email", userDetails.getEmail());
		mav.setViewName("lehrfach");
		mav.addObject("modulId", modulId);
		List<Course> courseList = courseRepository.findByModulId(modulId);
		
		mav.addObject("courseList", courseList);
		List<Group> groupList = new ArrayList<Group>();
		for (Course course : courseList)
		{
			groupList.addAll(groupRepository.findByCourseId(course.getCourseId()));
		}
		mav.addObject("groupList", groupList);
		List<Zeit> zeitList = new ArrayList<Zeit>();
		for (Group group : groupList)
		{
			zeitList.addAll(zeitRepository.findByGroupId(group.getGroupId()));
		}
		zeitList = checkZeit(zeitList);
		mav.addObject("zeitList", zeitList);
		List<LehrfachAnmelden> anmeldenList = anmeldungRepository.findByEmail(userDetails.getEmail());
		for (LehrfachAnmelden la : anmeldenList)
		{
			groupIdList.add(la.getGroupId());
		}
		mav.addObject("groupIdList", groupIdList);
		return mav;
	}
	
	@RequestMapping(value="/lehrfachAnmelden", method=RequestMethod.GET)
	public ModelAndView lehrfachAnmelden(@RequestParam(value="groupId") String groupId, @RequestParam(value="modulId") String modulId)
	{
		
		ModelAndView mav = lehrfach(modulId);
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userEmail = userDetails.getEmail();
		LehrfachAnmelden la = new LehrfachAnmelden();
		la.setEmail(userEmail);
		la.setGroupId(groupId);
		System.out.println("LehrfachAnmelden");
		if (anmeldungRepository.findByGroupId(groupId) == null)
		{
			System.out.println("Saved!!");
			anmeldungRepository.save(la);
			groupIdList.add(groupId);
		}
		mav.addObject("groupIdList", groupIdList);
		return mav;
	}
	
	@RequestMapping(value="lehrfachAbmelden", method=RequestMethod.GET)
	public ModelAndView lehrfachAbmelden(@RequestParam(value="groupId") String groupId, @RequestParam(value="modulId") String modulId)
	{
		ModelAndView mav = lehrfachAnmelden(groupId, modulId);
		
		LehrfachAnmelden la = anmeldungRepository.findByGroupId(groupId);
		if (la != null)
		{
			anmeldungRepository.delete(la);
			for (int i = 0; i < groupIdList.size(); i++)
			{
				if (groupIdList.get(i).equals(groupId))
				{
					groupIdList.remove(i);
					break;
				}
			}
		}
		return mav;
	}
	private List<Zeit> checkZeit(List<Zeit> zeitList)
	{
		List<Zeit> newZeitList = new ArrayList<Zeit>();
		for (Zeit zeit : zeitList)
		{
			boolean check = false;
			for (Zeit newZeit : newZeitList)
			{
				if (zeit.getBegin().compareTo(newZeit.getBegin()) == 0 && zeit.getEnd().compareTo(newZeit.getEnd()) == 0 && zeit.getAddress().equals(newZeit.getAddress()))
				{
					check = true;
				}
			}
			if (!check)
			{
				
				newZeitList.add(zeit);
			}
		}
		return newZeitList;
		
	}
}
