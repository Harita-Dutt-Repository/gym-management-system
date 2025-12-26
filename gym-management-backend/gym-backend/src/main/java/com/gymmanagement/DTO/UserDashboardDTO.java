package com.gymmanagement.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserDashboardDTO {

    private String username;
    private String membershipName;
    private LocalDate joinDate;
    private String status;
    private BigDecimal lastPaymentAmount;
    private LocalDate nextDueDate;
    private String dietDescription;

    // getters & setters 
    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the membershipName
     */
    public String getMembershipName() {
        return membershipName;
    }

    /**
     * @param membershipName the membershipName to set
     */
    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    /**
     * @return LocalDate return the joinDate
     */
    public LocalDate getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
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

    /**
     * @return BigDecimal return the lastPaymentAmount
     */
    public BigDecimal getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    /**
     * @param lastPaymentAmount the lastPaymentAmount to set
     */
    public void setLastPaymentAmount(BigDecimal lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
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
     * @return String return the dietDescription
     */
    public String getDietDescription() {
        return dietDescription;
    }

    /**
     * @param dietDescription the dietDescription to set
     */
    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }

}
