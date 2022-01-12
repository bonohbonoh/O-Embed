package com.example.OEmbed.controller;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.service.OembedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OembedController {
    @Autowired
    OembedService embedService;

    @GetMapping("/api/socialembed")
    public ResponseEntity<ResponseDto> embedApi(@RequestParam String url) throws Exception {
        return new ResponseEntity<ResponseDto>(embedService.callEmbedProcess(url), HttpStatus.OK);
    }
}
