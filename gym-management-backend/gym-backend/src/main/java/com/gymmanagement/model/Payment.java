package com.gymmanagement.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each payment is linked to one member
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private FeePackage feePackage;

    // Amount paid
    @Column(nullable = false)
    private double amount;

    // Date of payment
    @Column(nullable = false)
    private LocalDate paymentDate;

    private LocalDate nextDueDate;

    private String status;

    public Payment() {
    }

    public Payment(Member member, FeePackage feePackage, double amount, LocalDate paymentDate, LocalDate nextDueDate, String status) {
        this.member = member;
        this.feePackage = feePackage;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.nextDueDate = nextDueDate;
        this.status = status;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return LocalDate return the nextDueDate
     */
    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    /**
     * @param nextDueDate the nextDueDate to set
     */
    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    /**
     * @return FeePackage return the feePackage
     */
    public FeePackage getFeePackage() {
        return feePackage;
    }

    /**
     * @param feePackage the feePackage to set
     */
    public void setFeePackage(FeePackage feePackage) {
        this.feePackage = feePackage;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
