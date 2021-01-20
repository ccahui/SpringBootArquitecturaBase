package com.example.demo2.rest_controller.exception;

public class FieldError {

	private String name;
	private Object rejectValue;
	private	String message;
	public FieldError(String name, Object rejectValue, String message) {
		this.name = name;
		this.rejectValue = rejectValue;
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getRejectValue() {
		return rejectValue;
	}
	public void setRejectValue(Object rejectValue) {
		this.rejectValue = rejectValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
 }
