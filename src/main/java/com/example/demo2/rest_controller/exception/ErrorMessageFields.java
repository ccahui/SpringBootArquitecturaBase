package com.example.demo2.rest_controller.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorMessageFields extends ErrorMessage {
	private static final String DESCRIPTION = "Bad Request, fields Error";
	
	private List<FieldError>  fieldsError = new ArrayList<>();
	
	public ErrorMessageFields(MethodArgumentNotValidException exception, String path) {
		
		super(exception.getClass().getSimpleName(), DESCRIPTION, path);
		this.setFieldsError(exception);
	}
	
	public List<FieldError> getFieldsError(){
		return fieldsError;
	}

	 private void setFieldsError(MethodArgumentNotValidException exception) {
		 FieldError error;
	    	for( org.springframework.validation.FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
	    		error = new FieldError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
	        	fieldsError.add(error);
	    	}   	
	    }
}
