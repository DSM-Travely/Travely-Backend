package com.example.travely.controller;

import com.example.travely.dto.UserSignInRequest;
import com.example.travely.dto.UserSignUpRequest;
import com.example.travely.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public void signUp(@RequestBody UserSignUpRequest request) {
        userService.signUp(request);
    }

    @PostMapping("/auth")
    public void signIn(@RequestBody UserSignInRequest request) {
        userService.signIn(request);
    }
}
