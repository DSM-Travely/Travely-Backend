package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewResponse {
    private Integer id;
    private String mainImage;
    private String profileImage;
    private String reviewer;
    private String title;
    private String address;
    private List<String> keywords;
    private String content;
}
