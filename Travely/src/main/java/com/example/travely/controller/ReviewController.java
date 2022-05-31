package com.example.travely.controller;

import com.example.travely.dto.ReviewListResponse;
import com.example.travely.dto.SaveReviewRequest;
import com.example.travely.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public void postReview(@RequestBody SaveReviewRequest request) {
        reviewService.saveReview(request);
    }

    @GetMapping
    public ReviewListResponse getReviewList(@RequestParam String keyword) {
        return reviewService.getReviews(keyword);
    }
}
