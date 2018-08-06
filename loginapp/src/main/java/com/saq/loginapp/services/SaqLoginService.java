package com.saq.loginapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.saq.loginapp.data.SaqDAO;
import com.saq.loginapp.util.SaqUtils;

@Service
public class SaqLoginService {
	
	@Autowired
	SaqDAO repository;
	
	public boolean isValidUser(String strUsername, String strPassword, ModelMap model) {
		if (strUsername != null && strPassword != null && !strUsername.equals("") && !strPassword.equals("")) {
			String strPassKey = (String) model.get("AES_KEY");
			strPassword = SaqUtils.decryptAES(strPassword, strPassKey);
        }
		model.put("username", strUsername);
        model.put("password", strPassword);
        if(strUsername.contains("!")) {
        	throw new RuntimeException("Invalid user - " + strUsername);
        }
		return repository.isValidUser(strUsername, strPassword);
	}
}