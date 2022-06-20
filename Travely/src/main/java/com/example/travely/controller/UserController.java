package com.example.travely.controller;

import com.example.travely.dto.ProfileResponse;
import com.example.travely.dto.UserSignInRequest;
import com.example.travely.dto.UserSignInResponse;
import com.example.travely.dto.UserSignUpRequest;
import com.example.travely.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public void signUp(@RequestBody UserSignUpRequest request) {
        userService.signUp(request);
    }

    @PostMapping("/auth")
    public UserSignInResponse signIn(@RequestBody UserSignInRequest request) {
        return userService.signIn(request);
    }

    @GetMapping("/user")
    public ProfileResponse getProfile() { return userService.getProfile(); }

    @DeleteMapping("/user")
    public void deleteUser() { userService.deleteUser(); }
}
