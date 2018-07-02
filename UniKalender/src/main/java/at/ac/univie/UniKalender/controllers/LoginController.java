package at.ac.univie.UniKalender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.repositorys.UserRepository;
import at.ac.univie.UniKalender.security.CustomUserDetails;

/**
 * Created by uygarsenocak on 06.04.16.
 */
@Controller
public class LoginController {
	
	private CustomUserDetails userDetails;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView();
		userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		mav.setViewName("home");
		mav.addObject("email", userDetails.getEmail());
		return mav;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping(value = "/anmelden", method = RequestMethod.GET)
    public String anmelden() {
        return "anmelden";
    }
    
    @RequestMapping(value = "/anmeldeUser", method = RequestMethod.POST)
    public String anmeldeUser(@ModelAttribute("username") String username, @ModelAttribute("email") String email, @ModelAttribute("password") String password) {
    	User user = new User();
    	user.setEmail(email);
    	user.setUsername(username);
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String bcryptPassword = passwordEncoder.encode(password);
    	user.setPassword(bcryptPassword);
    	userRepository.save(user);
        return "login";
    }
}
