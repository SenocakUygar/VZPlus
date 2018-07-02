package at.ac.univie.UniKalender.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.models.CalendarEvents;
import at.ac.univie.UniKalender.models.LehrfachAnmelden;
import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.models.UserStudiumInformation;
import at.ac.univie.UniKalender.models.MyJAXBModels.Zeit;
import at.ac.univie.UniKalender.repositorys.LehrfachAnmeldenRepository;
import at.ac.univie.UniKalender.repositorys.UserRepository;
import at.ac.univie.UniKalender.repositorys.UserStudiumInformationRepository;
import at.ac.univie.UniKalender.repositorys.ZeitRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;

@Controller
public class CalendarControlle {
	private CustomUserDetails userDetails;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserStudiumInformationRepository userStudiumInformationRepository;
	@Autowired
	private LehrfachAnmeldenRepository anmeldenRepository;
	@Autowired
	private ZeitRepository zeitRepository;
	
	
	
	@RequestMapping(value="/calendar", method = RequestMethod.GET)
    public ModelAndView homePage() {
		
		ModelAndView mav = new ModelAndView();
		userDetails =
				 (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User user = userRepository.findByEmail(userDetails.getEmail());
		
		
		mav.setViewName("calendar");
		mav.addObject("email", userDetails.getEmail());
		mav.addObject("studiumInformation", userStudiumInformationRepository.findAllByUserId(user.getId()));
		return mav;
    }
	
	@RequestMapping(value="/removeModule", method = RequestMethod.GET)
    public ModelAndView removeModule(@RequestParam(value="userId") Long id, @RequestParam(value="fachgebietId") String fachgebietId) {		
		
		List<UserStudiumInformation> studiumInfoList = userStudiumInformationRepository.findAllByUserId(id);
		for (UserStudiumInformation studiumInfo : studiumInfoList)
		{
			if (studiumInfo.getFachgebietId().equals(fachgebietId))
			{
				userStudiumInformationRepository.delete(studiumInfo);
				break;
			}
		}
		ModelAndView mav = homePage();
		return mav;
    }
	
	@RequestMapping(value="/getEvents", method=RequestMethod.GET)
	public @ResponseBody List<CalendarEvents> getEvents()
	{
		List<CalendarEvents> events = new ArrayList<CalendarEvents>();	
		String userEmail = userDetails.getEmail();
		List<LehrfachAnmelden> anmeldenList = anmeldenRepository.findByEmail(userEmail);
		List<Zeit> zeitList = new ArrayList<Zeit>();
		for (LehrfachAnmelden la : anmeldenList)
		{
			
			zeitList.addAll(zeitRepository.findByGroupId(la.getGroupId()));
		}
		for (Zeit zeit : zeitList)
		{
			CalendarEvents event = new CalendarEvents();
			event.setStart(zeit.getBegin());
			event.setEnd(zeit.getEnd());
			event.setTitle(zeit.getTitle());
			event.setOrt(zeit.getAddress() + " " + zeit.getRoom());
			boolean kont = true;
			for (CalendarEvents e : events)
			{
				if (e.getStart().equals(event.getStart()) && e.getEnd().equals(event.getEnd()))
				{
					kont = false;
					break;
				}
			}
			if (kont)
				events.add(event);
		}
		
		return events;
	}
	
	

}
