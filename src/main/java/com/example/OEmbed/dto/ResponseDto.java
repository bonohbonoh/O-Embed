package com.example.OEmbed.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String type;
    private String version;
    private String author_name;
    private String author_url;
    private String html;
    private Integer width;
    private Integer height;
    private String provider_name;
    private String provider_url;

}
