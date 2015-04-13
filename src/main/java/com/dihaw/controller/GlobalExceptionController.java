package com.dihaw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.dihaw.exception.CustomGenericException;

@ControllerAdvice
public class GlobalExceptionController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static String GENERIC_ERROR_VIEW= "error/generic_error";
	private static String ERROR_VIEW = "error";
	private static String ERROR_CODE = "errorCode";
	private static String ERROR_MESSAGE = "errorMessage";
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException e) {
		
		logger.info("------------ handleCustomException");
		
		ModelAndView model = new ModelAndView(ERROR_VIEW);
		
		model.addObject(ERROR_CODE, e.getErrorCode());
		model.addObject(ERROR_MESSAGE, e.getErrorMessage());
 
		return model;
	}
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception e) {
		
		logger.info("------------ handleAllException");
		
		ModelAndView model = new ModelAndView(GENERIC_ERROR_VIEW);
 
		return model;
	}
	
}
