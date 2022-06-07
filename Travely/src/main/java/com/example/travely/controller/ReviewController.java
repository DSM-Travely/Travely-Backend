package com.example.travely.controller;

import com.example.travely.dto.ReviewListResponse;
import com.example.travely.dto.ReviewDetailResponse;
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
    public ReviewListResponse getReviewList(@RequestParam(defaultValue = "%") String keyword) {
        return reviewService.getReviews(keyword);
    }

    @GetMapping("/{reviewId}")
    public ReviewDetailResponse getReviewDetail(@PathVariable Integer reviewId) { return reviewService.getReviewDetail(reviewId); }

    @GetMapping("/mylist")
    public ReviewListResponse getMyReviewList() { return reviewService.getMyReviewList(); }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Integer reviewId) { reviewService.deleteReview(reviewId);}
}
