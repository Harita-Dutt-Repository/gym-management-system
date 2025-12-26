package com.gymmanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.model.FeePackage;
import com.gymmanagement.model.Member;
import com.gymmanagement.model.Payment;
import com.gymmanagement.service.FeePackageService;
import com.gymmanagement.service.MemberService;
import com.gymmanagement.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService paymentService;
    private final MemberService memberService;
    private final FeePackageService feePackageService;

    public PaymentController(PaymentService paymentService, MemberService memberService, FeePackageService feePackageService) {
        this.paymentService = paymentService;
        this.memberService = memberService;
        this.feePackageService = feePackageService;
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    // Get payments for specific member
    @GetMapping("/member/{memberId}")
    public List<Payment> getForMember(@PathVariable Long memberId) {
        return paymentService.getAll().stream()
                .filter(p -> p.getMember().getId().equals(memberId))
                .toList();
    }

    // Add new payment
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) {

        Long memberId = Long.parseLong(request.get("memberId"));
        double amount = Double.parseDouble(request.get("amount"));
        Long packageId = Long.parseLong(request.get("package_Id"));
        String status = new String(request.get("status"));

        Optional<Member> member = memberService.getMember(memberId);
        if (member.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid member ID");
        }

        Optional<FeePackage> feePackage = feePackageService.getById(packageId);
        if (feePackage.isEmpty()) {
            return ResponseEntity.badRequest().body("Fee Package is not Valid");
        }

        Payment payment = new Payment(
                member.get(),
                feePackage.get(),
                amount,
                LocalDate.now(),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 3, LocalDate.now().getDayOfMonth()),
                status
        );

        return ResponseEntity.ok(paymentService.save(payment));
    }
}
