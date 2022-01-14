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
public class YoutubeRulService implements IPlatformHtmlWriter{
    @Value("${URL.youtube}")
    private String YOUTUBE_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {
        // 유튜브 포스트 확인
        boolean isYoutubePost = Pattern.compile("(https://www.youtube.com/watch?.*)").matcher(paramUrl).find();
        if (!isYoutubePost) {
            throw new UrlMissMatchException("잘못된 Url 입니다.");
        }

        // API 요청
        String response = restTemplate.getForObject(YOUTUBE_URL + paramUrl, String.class);

        return objectMapper.readValue(response, ResponseDto.class);
    }

}
