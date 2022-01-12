package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Service
public class OembedService {

    private final static String TWITTER_URL = "https://publish.twitter.com/oembed?url=";

    public String getPlatformName(String requestUri) throws URISyntaxException {

        URI uri = new URI(requestUri);
        String platform = uri.getHost();
        if (platform.contains("www.")) {
            platform = platform.replaceAll("www.", "");
        }
        if (platform.contains(".com")) {
            platform = platform.replaceAll(".com", "");
        }
        return platform;
    }

    public ResponseDto callEmbedProcess(String requestUri) throws Exception {

        String platform = getPlatformName(requestUri);
        if (platform.equals("twitter")) {
            return getTwitterHTML(requestUri);
        }
        throw new UrlMissMatchException("nope");
    }

    private ResponseDto getTwitterHTML(String paramUrl) throws Exception{

        // 트위터 포스트 확인
        boolean isTwitterPost = Pattern.compile("(https://twitter.com/.*/status/.*?)").matcher(paramUrl).find();
        if (!isTwitterPost) {
            throw new UrlMissMatchException("잘못된 Url형식 입니다.");
        }

        RestTemplate template = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // API 요청
        String response = template.getForObject(TWITTER_URL + paramUrl, String.class);

        return objectMapper.readValue(response, ResponseDto.class);
    }
}
