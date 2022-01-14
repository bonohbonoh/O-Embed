package com.example.OEmbed.service;

import com.example.OEmbed.dto.ResponseDto;
import com.example.OEmbed.exception.UrlMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class RequestUrlFactory {

    private final TwitterUrlService twitterUrlService;
    private final InstagramUrlService instagramUrlService;
    private final YoutubeRulService youtubeRulService;
    private final VimeoUrlService vimeoUrlService;

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
                return twitterUrlService.requestApi(requestUri);
            case "youtube":
                return youtubeRulService.requestApi(requestUri);
            case "instagram":
                return instagramUrlService.requestApi(requestUri);
            case "vimeo":
                return vimeoUrlService.requestApi(requestUri);
            default:
                throw new UrlMissMatchException("잘못된 Url 입니다.");
        }

    }

}
