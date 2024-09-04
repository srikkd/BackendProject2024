package com.example.backendproject.exceptions;

import com.example.backendproject.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<?> handleNotFoundException(NotFoundException notFoundException){
        System.out.println("Not found exception happened !!!");

        return new ResponseEntity<>(
                new ExceptionDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}
