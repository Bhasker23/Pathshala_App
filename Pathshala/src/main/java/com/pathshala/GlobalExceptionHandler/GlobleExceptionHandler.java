package com.pathshala.GlobalExceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ExceptionDetails> loginException(LoginException ex, WebRequest wr){
		
		
		ExceptionDetails exceptionDetails = new ExceptionDetails();
		exceptionDetails.setMessage(ex.getMessage());
		exceptionDetails.setDescription(wr.getDescription(false));
		exceptionDetails.setLocalDate(LocalDate.now());

		return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
		
		
	}
	
}
