package com.example.travely.entity.review;

import com.example.travely.dto.ReviewResponse;

import java.util.List;

public interface ReviewRepositoryExtension {
    List<ReviewResponse> findAllByTitle(String keyword);
}
