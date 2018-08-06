package com.saq.loginapp.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SaqExceptionHandler {

	Logger logger = Logger.getLogger(SaqExceptionHandler.class);
	
	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest request, Exception e, Model model) {
		logger.error("Request " + request.getRequestURL() + " threw an exception: " + e);
		model.addAttribute("exception", e.getMessage());
		return "error/error";
	}
}
