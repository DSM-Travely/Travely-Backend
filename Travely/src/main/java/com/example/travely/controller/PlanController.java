package com.example.travely.controller;

import com.example.travely.dto.PlanListResponse;
import com.example.travely.dto.SavePlanRequest;
import com.example.travely.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    @PostMapping
    public void postPlan(@RequestBody SavePlanRequest request) {
        planService.savePlan(request);
    }

    @GetMapping
    public PlanListResponse getPlanList() {
        return planService.getPlanList();
    }

    @PatchMapping("/{planId}")
    public void updatePlanState(@PathVariable Integer planId) {
        planService.updatePlan(planId);
    }

    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Integer planId) {
        planService.deletePlan(planId);
    }

}
