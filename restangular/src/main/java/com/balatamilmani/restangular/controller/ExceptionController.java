package com.balatamilmani.restangular.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation") // 409
	@ExceptionHandler(Exception.class)
	public String conflict() {
		System.out.println("Exception happened");
		return "file not exists";
	}
}
