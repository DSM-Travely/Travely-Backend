package com.example.travely.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "token expired")
public class JwtTokenExpiredException extends RuntimeException {
}
