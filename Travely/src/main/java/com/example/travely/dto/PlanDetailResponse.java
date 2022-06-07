package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanDetailResponse {
    private String title;
    private String address;
    private String content;
}
