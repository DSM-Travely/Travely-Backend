package com.example.travely.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SavePlanRequest {
    private String title;
    private String address;
    private String content;
}
