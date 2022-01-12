package com.example.OEmbed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "URL Miss Matching")
public class UrlMissMatchException extends RuntimeException{
    public UrlMissMatchException(String msg) {
        super(msg);
    }
}