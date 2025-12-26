package com.gymmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymmanagement.model.DietPlan;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {

    Optional<DietPlan> findByMemberId(Long memberId);

}
