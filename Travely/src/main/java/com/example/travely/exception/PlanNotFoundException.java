package com.example.travely.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "plan not found")
public class PlanNotFoundException extends RuntimeException {
}
