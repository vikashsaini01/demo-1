package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class BaseController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Map<String,Object>> handleException(Exception exception, HttpServletRequest request){		
		logger.error("Exception occurred at url {} processing", request.getServletPath());
		Map<String, Object> map = new HashMap<>();
		map.put("Error at URL "+request.getServletPath(), exception.getMessage());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
