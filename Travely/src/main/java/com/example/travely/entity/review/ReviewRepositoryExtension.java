package com.example.travely.entity.review;

import com.example.travely.dto.ReviewDetailResponse;
import com.example.travely.dto.ReviewResponse;
import com.example.travely.entity.user.User;

import java.util.List;

public interface ReviewRepositoryExtension {
    List<ReviewResponse> findAllByTitle(String keyword);
    ReviewDetailResponse findAllById(Integer id);
    List<ReviewResponse> findAllByUser(User user);
}
