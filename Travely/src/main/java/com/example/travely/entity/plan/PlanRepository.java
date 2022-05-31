package com.example.travely.entity.plan;

import com.example.travely.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Integer> {
    List<Plan> findAllByUser(User user);
}
