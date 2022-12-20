package com.pathshala.GlobalExceptionHandler;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ExceptionDetails {

	private String message;
	private String description;
	private LocalDate localDate;	
	
}
