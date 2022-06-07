package com.example.travely.controller;

import com.example.travely.dto.ImageListResponse;
import com.example.travely.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ImageListResponse uploadImages(List<MultipartFile> images) {
        return imageService.uploadImages(images);
    }
}
