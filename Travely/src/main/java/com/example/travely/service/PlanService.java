package com.example.travely.service;

import com.example.travely.dto.PlanListResponse;
import com.example.travely.dto.PlanResponse;
import com.example.travely.dto.SavePlanRequest;
import com.example.travely.entity.plan.Plan;
import com.example.travely.entity.plan.PlanRepository;
import com.example.travely.entity.user.User;
import com.example.travely.entity.user.UserRepository;
import com.example.travely.exception.PlanNotFoundException;
import com.example.travely.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public void savePlan(SavePlanRequest savePlanRequest) {
        Plan plan = Plan.builder()
                .title(savePlanRequest.getTitle())
                .address(savePlanRequest.getAddress())
                .content(savePlanRequest.getContent())
                .build();

        planRepository.save(plan);
    }

    public PlanListResponse getPlanList() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new);

        List<Plan> plans = planRepository.findAllByUser(user);

        List<PlanResponse> planResponses = plans.stream()
                .map(plan -> PlanResponse.builder()
                        .isChecked(plan.getIsChecked())
                        .title(plan.getTitle())
                        .build())
                .toList();

        return PlanListResponse.builder()
                .planResponses(planResponses)
                .build();
    }

    @Transactional
    public void updatePlan(Integer planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(PlanNotFoundException::new);

        plan.modifyIsChecked();
    }
}
