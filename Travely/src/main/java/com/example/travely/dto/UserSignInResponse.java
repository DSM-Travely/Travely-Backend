package com.example.travely.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInResponse {
    private String token;
}
