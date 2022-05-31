package com.example.travely.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class SaveReviewRequest {
    private String address;
    private List<String> keyword;
    private String title;
    private String content;
    private List<String> images;
}
