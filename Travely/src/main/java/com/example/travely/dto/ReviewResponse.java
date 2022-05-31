package com.example.travely.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private String mainImage;
    private String profileImage;
    private String reviewer;
    private String title;
    private String address;
    private List<String> keywords;
    private String content;
}
