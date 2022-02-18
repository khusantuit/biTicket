package com.railway.biticket.common.exception;

public class NotFoundException extends RuntimeException{
    private final Class type;

    public NotFoundException(String message, Class type) {
        super(message);
        this.type = type;
    }
}
