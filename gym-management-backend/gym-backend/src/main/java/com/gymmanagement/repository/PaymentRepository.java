package com.gymmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymmanagement.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findTopByMemberIdOrderByPaymentDateDesc(Long memberId);

}
