package com.example.travely.entity.review;

import com.example.travely.entity.keyword.Keyword;
import com.example.travely.entity.reviewimage.ReviewImage;
import com.example.travely.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String address;

    @Digits(integer = 2, fraction = 8)
    @NotNull
    private Double latitude;

    @Digits(integer = 3, fraction = 8)
    @NotNull
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImages;

    @OneToMany(mappedBy = "review")
    private List<Keyword> keywords;
}
