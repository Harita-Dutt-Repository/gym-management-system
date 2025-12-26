package com.gymmanagement.controller;

import com.gymmanagement.model.AdminUnavailability;
import com.gymmanagement.model.User;
import com.gymmanagement.service.AdminUnavailabilityService;
import com.gymmanagement.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/unavailability")
@CrossOrigin("*")
public class AdminUnavailabilityController {

    private final AdminUnavailabilityService unavailableService;
    private final UserService userService;

    public AdminUnavailabilityController(AdminUnavailabilityService unavailableService, UserService userService) {
        this.unavailableService = unavailableService;
        this.userService = userService;
    }

    // Get all unavailable dates
    @GetMapping
    public List<AdminUnavailability> getAll() {
        return unavailableService.getAll();
    }

    // Apply for leave
    @PostMapping
    public ResponseEntity<?> applyLeave(@RequestBody Map<String, String> request) {

        Long adminId = Long.parseLong(request.get("adminId"));
        LocalDate date = LocalDate.parse(request.get("date"));
        String reason = request.get("reason");

        Optional<User> admin = userService.getUser(adminId);

        if (admin.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid admin ID");
        }

        AdminUnavailability leave = new AdminUnavailability(
                admin.get(),
                date,
                reason
        );

        try {
            return ResponseEntity.ok(unavailableService.save(leave));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
