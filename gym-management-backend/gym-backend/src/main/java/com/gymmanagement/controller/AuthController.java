package com.gymmanagement.controller;

import com.gymmanagement.model.User;
import com.gymmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        Optional<User> user = userService.validateLogin(username, password);

        if (user.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Success response
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.get().getId());
        response.put("username", user.get().getUsername());
        response.put("role", user.get().getRole());

        return ResponseEntity.ok(response);
    }
}
