package com.example.travely.entity.user;

import com.example.travely.entity.plan.Plan;
import com.example.travely.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Size(max = 50)
    @NotNull
    private String email;

    @Size(max = 20)
    @NotNull
    private String nickname;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @NotNull
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Plan> plans;
}
