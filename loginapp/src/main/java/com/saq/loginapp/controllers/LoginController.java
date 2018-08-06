package com.saq.loginapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.saq.loginapp.services.SaqLoginService;
import com.saq.loginapp.util.AesUtil;

@Controller
@SessionAttributes("AES_KEY")
public class LoginController {

	@Autowired
	private SaqLoginService service;
	Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value="/welcome")
	public String redirectToLogin(ModelMap model) {
		if(model.get("AES_KEY") == null) {
			model.put("AES_KEY", AesUtil.random(8));
		}
		return "login/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String validateUser(@RequestParam String username, @RequestParam String password, ModelMap model, HttpSession session) {
		if(service.isValidUser(username, password, model)) {
			model.clear();
			session.setAttribute("USER_STATE", "LoggedIn");
			//return "forward:/showEmp";
			return "redirect:/showEmp";
		} else {
			model.put("errorMessage", "Invalid Credentials!!!");
			return "login/login";
		}
	}

	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest request, Exception e, Model model) {
		logger.error("Request " + request.getRequestURL() + " threw an exception: " + e);
		model.addAttribute("exception", e.getMessage());
		return "error/error-specific";
	}
}