package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.dto.TwitterDto;
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
public class TwitterUrlService implements IPlatformHtmlWriter {

    @Value("${URL.twitter}")
    private String TWITTER_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {

        // 트위터 포스트 확인
        boolean isTwitterPost = Pattern.compile("(https://twitter.com/.*/status/.*?)").matcher(paramUrl).find();
        if (!isTwitterPost) {
            throw new UrlMissMatchException("잘못된 Url 입니다.");
        }

        // API 요청
        String response = restTemplate.getForObject(TWITTER_URL + paramUrl, String.class);

        return objectMapper.readValue(response, ResponseDto.class);
    }

}
