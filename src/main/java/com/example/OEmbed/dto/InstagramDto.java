package com.example.OEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstagramDto extends ResponseDto {

    private Long author_id;
    private String media_id;
    private String title;
    private String thumbnail_url;
    private Integer thumbnail_width;
    private Integer thumbnail_height;

}
