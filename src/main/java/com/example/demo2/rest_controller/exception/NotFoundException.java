package com.example.demo2.rest_controller.exception;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
