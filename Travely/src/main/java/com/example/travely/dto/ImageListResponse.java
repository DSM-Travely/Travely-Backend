package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ImageListResponse {
    private final List<String> imageNames;
}
