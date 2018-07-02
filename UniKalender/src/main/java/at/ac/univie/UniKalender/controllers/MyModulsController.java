package at.ac.univie.UniKalender.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.EmailService.EmailService;
import at.ac.univie.UniKalender.models.ImportExport;
import at.ac.univie.UniKalender.models.ModulAuswahl;
import at.ac.univie.UniKalender.repositorys.ImportExportRepository;
import at.ac.univie.UniKalender.repositorys.ModulAuswahlRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;

@Controller
public class MyModulsController {
	private CustomUserDetails userDetails;
	@Autowired
	private ModulAuswahlRepository modulAuswahlRepository;
	@Autowired
	private ImportExportRepository ieRepository;
	private EmailService emailService = new EmailService();
	
	@RequestMapping(value="/myModuls", method = RequestMethod.GET)
    public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView();
		userDetails =
				 (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("email", userDetails.getEmail());
		List<ModulAuswahl> modulList = modulAuswahlRepository.findByUserEmail(userDetails.getEmail());
		int geschaftAnzahl = 0;
		for (ModulAuswahl ma : modulList)
		{
			if (ma.getStatus() == 1)
				geschaftAnzahl++;
		}
		mav.addObject("geschaftAnzahl", geschaftAnzahl);
		mav.addObject("nichtGeschaftAnzahl", modulList.size() - geschaftAnzahl);
		mav.addObject("Anzahl", modulList.size());
		mav.addObject("modulList", modulList);
		mav.setViewName("myModuls");
		return mav;
    }
	
	@RequestMapping(value="/modulStatus", method=RequestMethod.GET)
	public @ResponseBody String modulStatus(Model model, @ModelAttribute("select") int select, @ModelAttribute("modulId") String modulId, @ModelAttribute("title") String title)
	{
		System.out.println(select);
		System.out.println(modulId);
		System.out.println(title);
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int status = 0;
		if (select == 1)
			status = -1;
		else
			status = 1;
		modulAuswahlRepository.updateStatus(modulId, status);
		if (status == 1)
			return title + " Geschafft :) :) :)";
		else
			return title + " Noch nciht Geschafft!!";
	}
	@RequestMapping(value="/import", method = RequestMethod.GET)
    public ModelAndView importSemesterplan(@ModelAttribute("importnummer") Long importnummer) {
		
		ImportExport ie = ieRepository.findByImportId(importnummer);
		
		if (ie.getWem().equals(userDetails.getEmail()))
		{
			
			String vonEmail = ie.getVon();
			List<ModulAuswahl> list = modulAuswahlRepository.findByUserEmail(vonEmail);
			for (ModulAuswahl ma : list)
			{
				ModulAuswahl neue = new ModulAuswahl();
				neue.setModulId(ma.getModulId());
				neue.setSemester(ma.getSemester());
				neue.setStatus(-1);
				neue.setTitle(ma.getTitle());
				neue.setUserEmail(ie.getWem());
				modulAuswahlRepository.save(neue);
			}
			
			
		}
		ModelAndView mav = homePage();
		return mav;
	}
	
	@RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportSemesterplan(@ModelAttribute("email") String empfanger) {
		ModelAndView mav = homePage();
		ImportExport ie = new ImportExport();
		Long importNummer = getImportnummer(); 
		ie.setImportId(importNummer);
		ie.setVon(userDetails.getEmail());
		ie.setWem(empfanger);
		ie.setVerfuegbar(true);
		ieRepository.save(ie);	
		emailService.sendEmail(empfanger, importNummer);
		return mav;
	}
	
	private Long getImportnummer()
	{
		Long importNummer;
		do{
		Random r = new Random();
		int i = r.nextInt(9000);
		importNummer = new Long(i);
		
		}while(ieRepository.findByImportId(importNummer) != null);
		return importNummer;
	}
	
	
}
