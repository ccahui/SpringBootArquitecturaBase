package com.example.demo2.rest_controller.exception;

public class JwtException extends RuntimeException{

    private static final String DESCRIPTION = "Jwt exception";

    public JwtException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
