package com.example.OEmbed.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String url;
    private String title;
    private String author_name;
    private String author_url;
    private String html;
    private String width;
    private String height;
    private String type;
    private String cache_age;
    private String provider_name;
    private String provider_url;
    private String version;

}
