package com.example.travely.service;

import com.example.travely.dto.UserSignInRequest;
import com.example.travely.dto.UserSignInResponse;
import com.example.travely.dto.UserSignUpRequest;
import com.example.travely.entity.user.User;
import com.example.travely.entity.user.UserRepository;
import com.example.travely.exception.UserNotFoundException;
import com.example.travely.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(UserSignUpRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .imageUrl("https://www.city.kr/files/attach/images/161/701/416/022/a2c34aa75756074e20552ccbac6894e8.jpg")
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    public UserSignInResponse signIn(UserSignInRequest request) {
        User user = userRepository.findById(request.getEmail())
                .orElseThrow(UserNotFoundException::new);
        String accessToken = jwtTokenProvider.generateToken(user.getEmail());

        return UserSignInResponse.builder()
                .token(accessToken)
                .build();
    }

}
