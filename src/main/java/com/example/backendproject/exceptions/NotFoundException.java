package com.example.backendproject.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
