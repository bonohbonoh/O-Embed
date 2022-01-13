package com.example.OEmbed.controller;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.service.OembedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class OembedController {

    private final OembedService embedService;

    @GetMapping("/api/socialembed")
    public ResponseEntity<ResponseDto> embedApi(@RequestParam String url) throws URISyntaxException, JsonProcessingException {
        return new ResponseEntity<ResponseDto>(embedService.callEmbedProcess(url), HttpStatus.OK);
    }
}
