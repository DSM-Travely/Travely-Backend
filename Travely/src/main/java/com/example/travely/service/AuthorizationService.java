package com.example.travely.service;

import com.example.travely.entity.user.User;
import com.example.travely.entity.user.UserRepository;
import com.example.travely.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new);
    }

}