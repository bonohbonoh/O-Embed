package com.example.OEmbed.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionStatusDto {

    private String msg;

    private int code;
}
