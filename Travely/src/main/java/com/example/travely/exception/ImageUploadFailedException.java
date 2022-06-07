package com.example.travely.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Image Upload Failed Exception")
public class ImageUploadFailedException extends RuntimeException {
}
