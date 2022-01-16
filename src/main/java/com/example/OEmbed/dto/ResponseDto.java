package com.example.OEmbed.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseDto {

    private String version;
    private String type;
    private Integer width;
    private Integer height;
    private String title;
    private String url;
    private String author_name;
    private String author_url;
    private String html;
    private String provider_name;
    private String provider_url;

}
