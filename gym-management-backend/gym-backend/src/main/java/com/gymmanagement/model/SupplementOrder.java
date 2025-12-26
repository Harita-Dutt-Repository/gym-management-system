package com.gymmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supplement_orders")
public class SupplementOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member placing the order
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // Supplement purchased
    @ManyToOne
    @JoinColumn(name = "supplement_id", nullable = false)
    private Supplement supplement;

    // Admin who handled the order
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    private int quantity;

    private double totalPrice;

    private LocalDate orderDate;

    public SupplementOrder() {}

    public SupplementOrder(Member member, Supplement supplement, User admin, int quantity, double totalPrice, LocalDate orderDate) {
        this.member = member;
        this.supplement = supplement;
        this.admin = admin;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
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

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
