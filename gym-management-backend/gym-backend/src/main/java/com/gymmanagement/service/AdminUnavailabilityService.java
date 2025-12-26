package com.gymmanagement.service;

import com.gymmanagement.model.AdminUnavailability;
import com.gymmanagement.repository.AdminUnavailabilityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class AdminUnavailabilityService {

    private final AdminUnavailabilityRepository adminUnavailableRepo;

    public AdminUnavailabilityService(AdminUnavailabilityRepository adminUnavailableRepo) {
        this.adminUnavailableRepo = adminUnavailableRepo;
    }

    public List<AdminUnavailability> getAll() {
        return adminUnavailableRepo.findAll();
    }

    public AdminUnavailability save(AdminUnavailability leave) {

        YearMonth month = YearMonth.from(leave.getDate());
        long leavesThisMonth = adminUnavailableRepo.findAll().stream()
                .filter(l -> l.getAdmin().getId().equals(leave.getAdmin().getId()))
                .filter(l -> YearMonth.from(l.getDate()).equals(month))
                .count();

        if (leavesThisMonth >= 2) {
            throw new RuntimeException("Admin cannot take more than 2 leaves in a month");
        }

        return adminUnavailableRepo.save(leave);
    }
}
