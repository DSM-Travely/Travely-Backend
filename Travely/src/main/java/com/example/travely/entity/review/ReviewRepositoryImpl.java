package com.example.travely.entity.review;

import com.example.travely.dto.ReviewDetailResponse;
import com.example.travely.dto.ReviewResponse;
import com.example.travely.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.travely.entity.reviewimage.QReviewImage.reviewImage;
import static com.example.travely.entity.user.QUser.user;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.core.types.Projections.list;
import static com.example.travely.entity.review.QReview.review;
import static com.example.travely.entity.keyword.QKeyword.keyword1;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResponse> findAllByTitle(String keyword) {
        return queryFactory.select(
                constructor(
                        ReviewResponse.class,
                        reviewImage.imageUrl,
                        user.imageUrl,
                        user.nickname,
                        review.title,
                        review.address,
                        list(
                                keyword1.keyword
                        ),
                        review.content
                )
        )
                .from(review)
                .where(review.title.contains(keyword))
                .join(review.reviewImages, reviewImage)
                .join(review.keywords, keyword1)
                .join(review.user, user)
                .fetch();
    }

    @Override
    public ReviewDetailResponse findAllById(Integer id) {
        return  queryFactory.select(
                constructor(
                        ReviewDetailResponse.class,
                        list(
                                reviewImage.imageUrl
                        ),
                        review.title,
                        review.address,
                        list(
                                keyword1.keyword
                        ),
                        review.content
                )
        )
                .from(review)
                .where(review.id.eq(id))
                .join(review.reviewImages, reviewImage)
                .join(review.keywords, keyword1)
                .join(review.user, user)
                .fetchOne();
    }

    @Override
    public List<ReviewResponse> findAllByUser(User user1) {
        return queryFactory.select(
                        constructor(
                                ReviewResponse.class,
                                reviewImage.imageUrl,
                                user.imageUrl,
                                user.nickname,
                                review.title,
                                review.address,
                                list(
                                        keyword1.keyword
                                ),
                                review.content
                        )
                )
                .from(review)
                .where(review.user.eq(user1))
                .join(review.reviewImages, reviewImage)
                .join(review.keywords, keyword1)
                .join(review.user, user)
                .fetch();
    }
}