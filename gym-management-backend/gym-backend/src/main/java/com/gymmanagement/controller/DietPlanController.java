package com.gymmanagement.controller;

import com.gymmanagement.model.DietPlan;
import com.gymmanagement.model.Member;
import com.gymmanagement.model.User;
import com.gymmanagement.service.DietPlanService;
import com.gymmanagement.service.MemberService;
import com.gymmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet")
@CrossOrigin("*")
public class DietPlanController {

    private final DietPlanService dietService;
    private final MemberService memberService;
    private final UserService userService;

    public DietPlanController(
            DietPlanService dietService,
            MemberService memberService,
            UserService userService
    ) {
        this.dietService = dietService;
        this.memberService = memberService;
        this.userService = userService;
    }

    // Get all diet plans
    @GetMapping
    public List<DietPlan> getAll() {
        return dietService.getAll();
    }

    // Get diet plans for a member
    @GetMapping("/member/{memberId}")
    public List<DietPlan> getForMember(@PathVariable Long memberId) {
        return dietService.getForMember(memberId);
    }

    // Assign diet plan
    @PostMapping
    public ResponseEntity<?> assignDiet(@RequestBody Map<String, String> request) {

        Long memberId = Long.parseLong(request.get("memberId"));
        Long adminId = Long.parseLong(request.get("adminId"));
        String description = request.get("dietDescription");

        Optional<Member> member = memberService.getMember(memberId);
        Optional<User> admin = userService.getUser(adminId);

        if (member.isEmpty()) return ResponseEntity.badRequest().body("Invalid member id");
        if (admin.isEmpty()) return ResponseEntity.badRequest().body("Invalid admin id");

        DietPlan plan = new DietPlan(
                member.get(),
                admin.get(),
                description,
                LocalDate.now()
        );

        return ResponseEntity.ok(dietService.save(plan));
    }
}
