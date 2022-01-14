package com.example.OEmbed.service;

import com.example.OEmbed.dto.InstagramDto;
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
public class InstagramUrlService implements IPlatformHtmlWriter {
    @Value("${URL.instagram}")
    private String INSTAGRAM_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public InstagramDto requestApi(String paramUrl) throws URISyntaxException, JsonProcessingException {

        //인스타그램 포스트 확인
        boolean isInstagramPost = Pattern.compile("(https://www.instagram.com/p/.*?)").matcher(paramUrl).find();
        if (!isInstagramPost) {
            throw new UrlMissMatchException("올바른 Instagram 주소형식이 아닙니다.");
        }

        // API 요청

        String response = "";
        try {
            response = restTemplate.getForObject(INSTAGRAM_URL + paramUrl, String.class);
        } catch (HttpClientErrorException e) {
            throw new PostNotFoundException("존재하지 않는 게시물이거나 O-embed에 등록되지 않은 게시물입니다.");
        }

        return objectMapper.readValue(response, InstagramDto.class);
    }

}
