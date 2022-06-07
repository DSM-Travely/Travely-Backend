package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {
    private String nickname;
    private String email;
}
