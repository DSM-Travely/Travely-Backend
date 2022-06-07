package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanResponse {
    private Integer id;
    private String title;
    private Boolean isChecked;
}