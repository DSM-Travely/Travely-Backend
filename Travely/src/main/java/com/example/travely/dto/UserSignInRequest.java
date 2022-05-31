package com.example.travely.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignInRequest {
    private String email;
    private String password;
}
