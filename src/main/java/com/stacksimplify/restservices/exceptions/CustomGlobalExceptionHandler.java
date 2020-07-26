package com.stacksimplify.restservices.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomerErrorDetails customerErrorDetails= new CustomerErrorDetails(new Date(),"Validation failure",ex.getMessage());
		return new ResponseEntity<>(customerErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomerErrorDetails customerErrorDetails= new CustomerErrorDetails(new Date(),"Only Post Supported for this Method",ex.getMessage());
		return new ResponseEntity<>(customerErrorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,WebRequest request){
		CustomerErrorDetails customerErrorDetails= new CustomerErrorDetails(new Date(),"This username does not exist",ex.getMessage());
		return new ResponseEntity<>(customerErrorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleConstraintViolationExcetion(ConstraintViolationException ex,WebRequest request){
		CustomerErrorDetails customerErrorDetails= new CustomerErrorDetails(new Date(),"Userid should be greater than 0",ex.getMessage());
		return new ResponseEntity<>(customerErrorDetails,HttpStatus.NOT_FOUND);
	}
}
