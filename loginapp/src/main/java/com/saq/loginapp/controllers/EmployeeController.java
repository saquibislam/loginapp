package com.saq.loginapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saq.loginapp.services.FetchEmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	FetchEmployeeService fetchEmpService;
	
	@RequestMapping(value="/showEmp")
	public String showEmployees(ModelMap model, HttpSession session) {
		String userState = (String) session.getAttribute("USER_STATE");
		if(userState != null && userState.equals("LoggedIn")) {
			model.addAttribute("employeeList", fetchEmpService.fetch());
		} else {
			throw new RuntimeException("You are not authorized to view this page");
		}
		return "showDetails";
	}
}