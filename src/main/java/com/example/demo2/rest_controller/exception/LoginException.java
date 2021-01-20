package com.example.demo2.rest_controller.exception;

public class LoginException extends RuntimeException {
	
	private static final String DESCRIPTION = " Credenciales Incorrectas(401)";
	public LoginException(String detail) {
	    super(DESCRIPTION + ". " + detail);
	}
}
