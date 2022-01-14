package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface IPlatformHtmlWriter {
    ResponseDto requestApi(String requestUrl) throws URISyntaxException, JsonProcessingException;
}
