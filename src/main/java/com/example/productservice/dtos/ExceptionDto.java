package com.example.productservice.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExceptionDto {
    private HttpStatus statusCode;
    private String message;

    // constructor
    public ExceptionDto(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
