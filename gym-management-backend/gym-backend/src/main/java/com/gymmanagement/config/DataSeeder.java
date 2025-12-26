package com.gymmanagement.config;

import com.gymmanagement.model.User;
import com.gymmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create admin only if no admin exists
        boolean adminExists = userRepository.findAll().stream()
                .anyMatch(u -> u.getRole().equalsIgnoreCase("ADMIN"));

        if (!adminExists) {
            User admin = new User(
                    "admin",
                    "admin123",
                    "ADMIN"
            );

            userRepository.save(admin);

            System.out.println("✔ Default admin created: username=admin, password=admin123");
        } else {
            System.out.println("✔ Admin already exists.");
        }
    }
}
