package com.saq.loginapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.saq.loginapp.mvcbeans.Employee;
import com.saq.loginapp.services.RegistrationService;
import com.saq.loginapp.util.AesUtil;
import com.saq.loginapp.util.SaqUtils;

@Controller
@SessionAttributes("AES_KEY")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value="/welcome1")
	public String redirectToRegisteration(ModelMap model) {

		if(model.get("AES_KEY") == null) {
			model.put("AES_KEY", AesUtil.random(8));
		}

		model.put("cityList", registrationService.fetchCityList());

		//model.addAttribute("employee", new Employee());
		return "login/register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(Employee employee, ModelMap model, BindingResult result) {
		String strPassword = employee.getPassword();
		strPassword = SaqUtils.decryptAES(strPassword, (String) model.get("AES_KEY"));
		employee.setPassword(strPassword);
		
		registrationService.registerEmployee(employee);
		
		model.put("cityList", registrationService.fetchCityList());
		
		employee.setId(0);
		employee.setName("");
		employee.setUsername("");
		employee.setPassword("");
		employee.setSalary(0f);
		employee.setAddress(null);

		model.put("msgColor", "yellow");
		model.put("messageDesc", "Registration Success!");
		
		return "/login/register";
	}

	@ModelAttribute("employee")
	public Employee populateForm() {
		return new Employee();
	}
}