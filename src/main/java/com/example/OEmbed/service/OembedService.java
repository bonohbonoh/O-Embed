package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class OembedService {

    @Value("${URL.twitter}")
    private String TWITTER_URL;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private String getUrl(String requestUrl) throws URISyntaxException {
        URI uri = new URI(requestUrl);
        String platform = uri.getHost();
        if (platform == null) {
            throw new UrlMissMatchException("잘못된 Url 입니다.");
        }
        return platform;
    }

    private String getPlatformName(String requestUrl) throws URISyntaxException {

        String url = getUrl(requestUrl);

        if (url.contains("www.")) {
            url = url.replaceAll("www.", "");
        }
        if (url.contains(".com")) {
            url = url.replaceAll(".com", "");
        }
        return url;
    }

    public ResponseDto callEmbedProcess(String requestUri) throws URISyntaxException, JsonProcessingException {

        String platform = getPlatformName(requestUri);
        switch (platform) {
            case "twitter":
                return getTwitterHTML(requestUri);
            default:
                throw new UrlMissMatchException("잘못된 Url 입니다.");
        }

    }

    public ResponseDto getTwitterHTML(String paramUrl) throws URISyntaxException, JsonProcessingException {

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
