package com.example.travely.service;

import com.example.travely.dto.ReviewDetailResponse;
import com.example.travely.dto.ReviewListResponse;
import com.example.travely.dto.ReviewResponse;
import com.example.travely.dto.SaveReviewRequest;
import com.example.travely.entity.keyword.Keyword;
import com.example.travely.entity.keyword.KeywordRepository;
import com.example.travely.entity.review.Review;
import com.example.travely.entity.review.ReviewRepository;
import com.example.travely.entity.reviewimage.ReviewImage;
import com.example.travely.entity.reviewimage.ReviewImageRepository;
import com.example.travely.entity.user.User;
import com.example.travely.exception.ReviewNotFoundException;
import com.example.travely.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final KeywordRepository keywordRepository;
    private final ReviewImageRepository reviewImageRepository;

    private final AuthorizationService authorizationService;

    public void saveReview(SaveReviewRequest saveReviewRequest) {
        Review review = Review.builder()
                .address(saveReviewRequest.getAddress())
                .title(saveReviewRequest.getTitle())
                .content(saveReviewRequest.getContent())
                .build();

        Review savedReview = reviewRepository.save(review);

        List<Keyword> keywords = saveReviewRequest.getKeyword().stream()
                .map(keyword -> Keyword.builder()
                        .review(savedReview)
                        .keyword(keyword)
                        .build()).toList();

        keywordRepository.saveAll(keywords);

        List<ReviewImage> reviewImages = saveReviewRequest.getImages().stream()
                .map(imageUrl -> ReviewImage.builder()
                        .review(savedReview)
                        .imageUrl(imageUrl)
                        .build()).toList();

        reviewImageRepository.saveAll(reviewImages);
    }

    public ReviewListResponse getReviews(String keyword) {
        List<ReviewResponse> reviews = reviewRepository.findAllByTitle(keyword);

        return ReviewListResponse.builder()
                .reviewResponseList(reviews)
                .build();
    }

    public ReviewDetailResponse getReviewDetail(Integer reviewId) {
        ReviewDetailResponse review = reviewRepository.findAllById(reviewId);

        return ReviewDetailResponse.builder()
                .images(review.getImages())
                .title(review.getTitle())
                .address(review.getAddress())
                .keywords(review.getKeywords())
                .content(review.getContent())
                .build();
    }

    public ReviewListResponse getMyReviewList() {
        List<ReviewResponse> reviews = reviewRepository.findAllByUser(authorizationService.getUser());

        return  ReviewListResponse.builder()
                .reviewResponseList(reviews)
                .build();
    }

    public void deleteReview(Integer reviewId) {
        User user = authorizationService.getUser();

        Review review = reviewRepository.findById(reviewId)
                .filter(review1 -> review1.getUser().equals(user))
                .orElseThrow(ReviewNotFoundException::new);

        reviewRepository.delete(review);
    }
}
