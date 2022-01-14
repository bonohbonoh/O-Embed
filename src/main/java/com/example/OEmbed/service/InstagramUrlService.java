package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class InstagramUrlService implements IPlatformHtmlWriter {
    @Value("${URL.instagram}")
    private String INSTAGRAM_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {

        //인스타그램 포스트 확인
        boolean isInstagramPost = Pattern.compile("(https://www.instagram.com/p/.*?)").matcher(paramUrl).find();
        if (!isInstagramPost) {
            throw new UrlMissMatchException("올바른 주소형식이 아닙니다.");
        }

        // API 요청
        String response = restTemplate.getForObject(INSTAGRAM_URL + paramUrl, String.class);

        return objectMapper.readValue(response, ResponseDto.class);
    }

}
