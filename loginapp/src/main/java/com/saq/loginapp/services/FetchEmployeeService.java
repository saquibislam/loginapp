package com.saq.loginapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saq.loginapp.data.SaqDAO;
import com.saq.loginapp.mvcbeans.Employee;

@Service
public class FetchEmployeeService {
	
	@Autowired
	SaqDAO repository;

	public List<Employee> fetch() {		
		return repository.getAllEmployee();
	}
}