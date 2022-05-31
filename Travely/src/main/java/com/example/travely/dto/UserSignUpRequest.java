package com.example.travely.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpRequest {
    private String email;
    private String nickname;
    private String password;
}
