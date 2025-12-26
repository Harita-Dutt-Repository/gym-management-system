package com.gymmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diet_plans")
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member to whom diet plan is assigned
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Admin/Trainer who assigned the plan
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    // Full diet description text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String dietDescription;

    private LocalDate assignedDate;

    public DietPlan() {}

    public DietPlan(Member member, User admin, String dietDescription, LocalDate assignedDate) {
        this.member = member;
        this.admin = admin;
        this.dietDescription = dietDescription;
        this.assignedDate = assignedDate;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getDietDescription() {
        return dietDescription;
    }

    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
}
