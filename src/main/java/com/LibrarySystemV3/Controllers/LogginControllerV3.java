package com.LibrarySystemV3.Controllers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.LibrarySystemV3.Dao.UserV3Dao;
import com.LibrarySystemV3.models.AuthenticationDto;
import com.LibrarySystemV3.models.User;

@Controller
@RequestMapping("/auth")
public class LogginControllerV3 {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	UserV3Dao userV3Dao;

	@GetMapping(value = "/loginRest/{email}/{password}", produces = "application/json")
	public ResponseEntity<?> login(@PathVariable(value = "email") String email,
			@PathVariable(value = "password") String password, HttpSession httpSession) {
		try {
			User user = userV3Dao.getByEmail(email);
			AuthenticationDto auDto = new AuthenticationDto();
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				httpSession.setAttribute("User", user);

				auDto.setStatus(true);
				if (user.getRole().equals("admin")) {
					auDto.setRole("Admin");
				} else if (user.getRole().equals("member")) {
					auDto.setRole("Member");
				}
				return new ResponseEntity(auDto, HttpStatus.OK);

			} else {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error("fail to load", ex);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

}
