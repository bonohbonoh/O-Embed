package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class OembedService {

    private final RequestUrlFactory requestUrlFactory;

    public ResponseDto callUrl(String requestUrl) throws URISyntaxException, JsonProcessingException{
        return requestUrlFactory.callEmbedProcess(requestUrl);
    }
}
