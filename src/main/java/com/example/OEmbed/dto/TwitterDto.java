package com.example.OEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TwitterDto extends ResponseDto {

    private String url;
    private String cache_age;

}
