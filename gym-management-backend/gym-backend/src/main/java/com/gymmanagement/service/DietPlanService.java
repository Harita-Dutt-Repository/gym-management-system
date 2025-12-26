package com.gymmanagement.service;

import com.gymmanagement.model.DietPlan;
import com.gymmanagement.repository.DietPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DietPlanService {

    private final DietPlanRepository dietPlanRepository;

    public DietPlanService(DietPlanRepository dietPlanRepository) {
        this.dietPlanRepository = dietPlanRepository;
    }

    public List<DietPlan> getAll() {
        return dietPlanRepository.findAll();
    }

    public List<DietPlan> getForMember(Long memberId) {
        return dietPlanRepository.findAll()
                .stream()
                .filter(d -> d.getMember().getId().equals(memberId))
                .toList();
    }

    public DietPlan save(DietPlan dietPlan) {
        dietPlan.setAssignedDate(LocalDate.now());
        return dietPlanRepository.save(dietPlan);
    }
}
