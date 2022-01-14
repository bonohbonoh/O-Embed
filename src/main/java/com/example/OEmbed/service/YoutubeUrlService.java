package com.example.OEmbed.service;

import com.example.OEmbed.dto.YoutubeDto;
import com.example.OEmbed.exception.PostNotFoundException;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class YoutubeUrlService implements IPlatformHtmlWriter {
    @Value("${URL.youtube}")
    private String YOUTUBE_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public YoutubeDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {

        // 유튜브 포스트 확인
        boolean isYoutubePost = Pattern.compile("(https://www.youtube.com/watch?.*)").matcher(paramUrl).find();
        if (!isYoutubePost) {
            throw new UrlMissMatchException("올바른 Youtube 주소 형식이 아닙니다.");
        }

        // API 요청
        String response = "";
        try {
            response = restTemplate.getForObject(YOUTUBE_URL + paramUrl, String.class);
        } catch (HttpClientErrorException e) {
            throw new PostNotFoundException("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");
        }


        return objectMapper.readValue(response, YoutubeDto.class);
    }

}
