package com.saq.loginapp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saq.loginapp.data.SaqDAO;
import com.saq.loginapp.mvcbeans.Employee;

@Service
public class RegistrationService {
	
	@Autowired
	SaqDAO repository;

	public List<String> fetchCityList() {
		ArrayList<String> cityList = new ArrayList<String>();
		cityList.add("New Delhi");
		cityList.add("Patna");
		cityList.add("Aligarh");
		cityList.add("Bangalore");
		cityList.add("Mumbai");
		cityList.add("Kolakata");
		cityList.add("Araria");
		Collections.sort(cityList);
		
		return cityList;
	}

	public void registerEmployee(Employee employee) {
		repository.addUser(employee);
	}
}