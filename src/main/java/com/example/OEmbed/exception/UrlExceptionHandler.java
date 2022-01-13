package com.example.OEmbed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UrlExceptionHandler {
    @ExceptionHandler(UrlMissMatchException.class)
    public ResponseEntity<ExceptionStatusDto> urlMissMatchException(UrlMissMatchException urlMissMatchException) {
        ExceptionStatusDto response = new ExceptionStatusDto(urlMissMatchException.getMessage(), 400);
        return new ResponseEntity<ExceptionStatusDto>(response, HttpStatus.BAD_REQUEST);
    }
}
