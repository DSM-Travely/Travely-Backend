package com.example.travely.service;

import com.example.travely.dto.PlanDetailResponse;
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

    private final AuthorizationService authorizationService;

    @Transactional
    public void savePlan(SavePlanRequest savePlanRequest) {
        User user = authorizationService.getUser();
        Plan plan = Plan.builder()
                .title(savePlanRequest.getTitle())
                .address(savePlanRequest.getAddress())
                .content(savePlanRequest.getContent())
                .user(user)
                .build();

        planRepository.save(plan);
    }

    public PlanListResponse getPlanList() {
        User user = authorizationService.getUser();

        List<Plan> plans = planRepository.findAllByUser(user);

        List<PlanResponse> planResponses = plans.stream()
                .map(plan -> PlanResponse.builder()
                        .isChecked(plan.getIsChecked())
                        .title(plan.getTitle())
                        .id(plan.getId())
                        .build())
                .toList();

        return PlanListResponse.builder()
                .planResponses(planResponses)
                .build();
    }

    @Transactional
    public void updatePlan(Integer planId) {
        User user = authorizationService.getUser();

        Plan plan = planRepository.findById(planId)
                .filter(plan1 -> plan1.getUser().equals(user))
                .orElseThrow(PlanNotFoundException::new);

        plan.modifyIsChecked();
    }

    public PlanDetailResponse getPlanDetail(Integer planId) {
        User user = authorizationService.getUser();

        Plan plan = planRepository.findById(planId)
                .filter(plan1 -> plan1.getUser().equals(user))
                .orElseThrow(PlanNotFoundException::new);

        return PlanDetailResponse.builder()
                .title(plan.getTitle())
                .address(plan.getAddress())
                .content(plan.getContent())
                .build();
    }

    public void deletePlan(Integer planId) {
        User user = authorizationService.getUser();

        Plan plan = planRepository.findById(planId)
                .filter(plan1 -> plan1.getUser().equals(user))
                .orElseThrow(PlanNotFoundException::new);

        planRepository.delete(plan);
    }
}
