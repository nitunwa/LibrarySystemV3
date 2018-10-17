package com.LibrarySystemV3.Controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.LibrarySystemV3.Dao.UserV3Dao;
import com.LibrarySystemV3.models.User;

@Controller
@RequestMapping("/auth")
public class LogginControllerV3 {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	UserV3Dao userV3Dao = new UserV3Dao();

	@RequestMapping(value = "/singin", method = RequestMethod.GET)
	public String singin(Model model) {
		return "singin";
	}

	@RequestMapping(value="singin" , method=RequestMethod.POST)
	public String singin(Model model, @RequestParam(value="email") String email,
			@RequestParam(value = "password") String password, HttpSession httpSession ) {
		
		try {
			User user = userV3Dao.getByEmail(email);
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				logger.info("valid user");
				httpSession.setAttribute("login user", user);
			}
				else if(user.getRole().equals("admin")) 
				{
	              logger.info("valid user");
	              return "redirect:/admin/showDashboard";
				}
				else if(user.getRole().equals("member")) 
				{
	              logger.info("valid user");
	              return "redirect:/library/showDashboard";
				}
				
			
			else {logger.info("user invalid");}
		}
		catch(Exception es) {
			logger.error("login fail", es);
			return "redirect:/login";
		}
		
		return "redirect:/login";
	}

}
