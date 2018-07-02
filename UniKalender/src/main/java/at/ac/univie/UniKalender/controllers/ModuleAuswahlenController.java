package at.ac.univie.UniKalender.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.models.ModulAuswahl;
import at.ac.univie.UniKalender.models.MyJAXBModels.Modul;
import at.ac.univie.UniKalender.repositorys.ModulAuswahlRepository;
import at.ac.univie.UniKalender.repositorys.ModulRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;

@Controller
public class ModuleAuswahlenController {

	private CustomUserDetails userDetails;
	@Autowired
	private ModulRepository modulRepository;
	@Autowired
	private ModulAuswahlRepository modulAuswahlRepository;
	
	@RequestMapping(value="/moduleAuswahlen", method=RequestMethod.GET)
	public ModelAndView moduleAuswahlen(@RequestParam(value="userId") String id, @RequestParam(value="fachgebietId") String fachgebietId)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("moduleAuswahlen");
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("email", userDetails.getEmail());
		List<Modul> modulList = modulRepository.findByFachGebietId(fachgebietId);
		
		List<ModulAuswahl> modulAuswahlList = modulAuswahlRepository.findByUserEmail(userDetails.getEmail());
		
		mav.addObject("modulAuswahlList", modulAuswahlList);
		mav.addObject("modulList", modulList);
		return mav;
	}
	
	
	@RequestMapping(value="/semesterAuswahlen", method=RequestMethod.GET)
	public @ResponseBody String semesterAuswahlen(Model model, @ModelAttribute("select") int select, @ModelAttribute("modulId") String modulId, @ModelAttribute("title") String title)
	{
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModulAuswahl ma = new ModulAuswahl();
		ma.setTitle(title);
		ma.setModulId(modulId);
		ma.setSemester(select);
		ma.setUserEmail(userDetails.getEmail());
		ma.setStatus(-1);
		modulAuswahlRepository.save(ma);
		return title + " wird in der Semester " + select + " hinzufügt";
	}
}
