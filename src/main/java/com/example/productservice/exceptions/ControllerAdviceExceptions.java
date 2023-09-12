package com.example.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.productservice.dtos.ExceptionDto;

@ControllerAdvice
public class ControllerAdviceExceptions {
    
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> notFoundExceptionHandler(NotFoundException exception) {
        // System.out.println("Not found exception occurred.");
        
        return new ResponseEntity<ExceptionDto>(
            new ExceptionDto(HttpStatus.NOT_FOUND, exception.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }
}
