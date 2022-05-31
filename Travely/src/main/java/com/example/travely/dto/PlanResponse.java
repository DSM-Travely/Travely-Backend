package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanResponse {
    private String title;
    private Boolean isChecked;
}