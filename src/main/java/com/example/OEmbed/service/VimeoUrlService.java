package com.example.OEmbed.service;

import com.example.OEmbed.dto.VimeoDto;
import com.example.OEmbed.exception.PostNotFoundException;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class VimeoUrlService implements IPlatformHtmlWriter {
    @Value("${URL.vimeo}")
    private String VIMEO_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public VimeoDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {

        // vimeo 포스트 확인
        boolean isVimeoPost = Pattern.compile("(https://vimeo.com/[0-9].*)").matcher(paramUrl).find();
        if (!isVimeoPost) {
            throw new UrlMissMatchException("올바른 Vimeo 주소 형식이 아닙니다.");
        }

        // API 요청

        String response = "";
        try {
            response = restTemplate.getForObject(VIMEO_URL + paramUrl, String.class);
        } catch (HttpClientErrorException e) {
            throw new PostNotFoundException("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");
        }

        return objectMapper.readValue(response, VimeoDto.class);
    }
}
