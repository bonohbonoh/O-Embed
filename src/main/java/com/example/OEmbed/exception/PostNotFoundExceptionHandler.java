package com.example.OEmbed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostNotFoundExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ExceptionStatusDto> postNotFoundException(PostNotFoundException postNotFoundException) {
        ExceptionStatusDto response = new ExceptionStatusDto(postNotFoundException.getMessage(), 400);
        return new ResponseEntity<ExceptionStatusDto>(response, HttpStatus.BAD_REQUEST);
    }
}
