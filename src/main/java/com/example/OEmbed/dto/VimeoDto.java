package com.example.OEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VimeoDto extends ResponseDto {

    private Long video_id;
    private String is_plus;
    private String account_type;
    private String description;
    private String thumbnail_url_with_play_button;
    private String uri;
    private String thumbnail_url;
    private String duration;
    private String upload_date;
    private Integer thumbnail_width;
    private Integer thumbnail_height;

}
