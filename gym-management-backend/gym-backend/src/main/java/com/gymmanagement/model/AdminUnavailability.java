package com.gymmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admin_unavailability")
public class AdminUnavailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Admin who applied leave
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    // Date of leave
    @Column(nullable = false)
    private LocalDate date;

    private String reason;

    public AdminUnavailability() {}

    public AdminUnavailability(User admin, LocalDate date, String reason) {
        this.admin = admin;
        this.date = date;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
