package at.ac.univie.UniKalender.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.models.UserStudiumInformation;
import at.ac.univie.UniKalender.models.MyJAXBModels.FachGebietModule;
import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungModule;
import at.ac.univie.UniKalender.repositorys.FachGebietRepository;
import at.ac.univie.UniKalender.repositorys.StudienRichtungRepository;
import at.ac.univie.UniKalender.repositorys.UserRepository;
import at.ac.univie.UniKalender.repositorys.UserStudiumInformationRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;

@Controller
public class StudienrichtungAuswahl {

	@Autowired
	StudienRichtungRepository studienRichtungRepository;
	@Autowired
	private FachGebietRepository fachGebietRepository;
	@Autowired
	private UserStudiumInformationRepository userStudiumInformationRepository;
	@Autowired
	private UserRepository userRepository;
	private List<StudienRichtungModule> list;
	private CustomUserDetails userDetails;
	
	
	@RequestMapping(value="/studienrichtungAuswahl", method=RequestMethod.GET)
	public ModelAndView getStudienrichtungPage()
	{
		
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userDetails.getEmail());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("email", userDetails.getEmail());
		list = studienRichtungRepository.findAll();
		mav.setViewName("studienrichtungAuswahl");
		mav.addObject("studienRichtungList", list);
		mav.addObject("studiumInformation", userStudiumInformationRepository.findAllByUserId(user.getId()));
		return mav;
	}
	@RequestMapping(value="/getStudienfachgebiet", method=RequestMethod.GET)
	public ModelAndView getStudienfachgebiet(@RequestParam(value="id") String id)
	{
		ModelAndView mav = getStudienrichtungPage();
		mav.setViewName("studienFachGebietAuswahl");
		List<FachGebietModule> fachGebietList = fachGebietRepository.findByRichtungId(id);
		mav.addObject("fachGebietList", fachGebietList);
		mav.addObject("studienRichtungId", id);
		mav.addObject("studienrichtungName", studienRichtungRepository.findOne(id).getTitle());
		mav.addObject("email", userDetails.getEmail());
		User user = userRepository.findByEmail(userDetails.getEmail());
		mav.addObject("studiumInformation", userStudiumInformationRepository.findAllByUserId(user.getId()));
		
		return mav;
	}
	
	@RequestMapping(value="/addStudienrichtung", method=RequestMethod.GET)
	public ModelAndView addStudienrichtung(@RequestParam(value="id") String id, @RequestParam(value="studienrichtungID") String studienrichtungID)
	{
		ModelAndView mav = getStudienrichtungPage();

		User user = userRepository.findByEmail(userDetails.getEmail());
		
		if (kontrolleAddStudienrichtung(user, studienrichtungID))
		{
			UserStudiumInformation studiumInfo = new UserStudiumInformation();
			studiumInfo.setId(user.getId());
			studiumInfo.setStudienrichtungId(studienrichtungID);
			studiumInfo.setStudienrichtungName(studienRichtungRepository.findOne(studienrichtungID).getTitle());
			studiumInfo.setFachgebietId(id);
			studiumInfo.setFachgebietName(fachGebietRepository.findOne(id).getTitle());
			userStudiumInformationRepository.save(studiumInfo);
		}
		mav.addObject("studiumInformation", userStudiumInformationRepository.findAllByUserId(user.getId()));
		mav.setViewName("studienrichtungAuswahl");
		
		return mav;
	}
	
	private boolean kontrolleAddStudienrichtung(User user, String studienrichtungID)
	{
		List<UserStudiumInformation> studiumInfoList = userStudiumInformationRepository.findAllByUserId(user.getId());
		for (UserStudiumInformation studiumInfo : studiumInfoList)
		{
			if (studiumInfo.getStudienrichtungId().equals(studienrichtungID))
			{
				return false;
			}
		}
		return true;
	}
}
