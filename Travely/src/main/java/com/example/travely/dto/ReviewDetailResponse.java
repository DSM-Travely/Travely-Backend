package com.example.travely.dto;

import com.example.travely.entity.keyword.Keyword;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewDetailResponse {
    private List<String> images;
    private String title;
    private String address;
    private List<String> keywords;
    private String content;
}
