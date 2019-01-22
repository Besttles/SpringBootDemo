package com.xiaour.spring.boot.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xiaour.spring.boot.exception.NotKnownException;
import com.xiaour.spring.boot.exception.OApiException;

@ControllerAdvice
public class MyExceptionHandler {

	//@ExceptionHandler(NotKnownException.class)
	public Map<String,String> handleException() {
		Map<String,String> map = new HashMap<>();
		map.put("code", "OPI");
		return map;
	}
	@ExceptionHandler(NotKnownException.class)
	public String getErrorView(Exception e,HttpServletRequest request) {
		Map<String,String> map = new HashMap<>();
		request.setAttribute("javax.servlet.error.status_code", "404");
		map.put("code", "OPI");
		map.put("message", e.getMessage());
		return "forward:/error";
	}
}
