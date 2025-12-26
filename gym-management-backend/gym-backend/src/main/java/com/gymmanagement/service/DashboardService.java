package com.gymmanagement.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gymmanagement.DTO.UserDashboardDTO;
import com.gymmanagement.model.Member;
import com.gymmanagement.model.User;
import com.gymmanagement.repository.DietPlanRepository;
import com.gymmanagement.repository.FeePackageRepository;
import com.gymmanagement.repository.MemberRepository;
import com.gymmanagement.repository.PaymentRepository;
import com.gymmanagement.repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final DietPlanRepository dietPlanRepository;
    private final FeePackageRepository feePackageRepository;

    public DashboardService(
            UserRepository userRepository,
            MemberRepository memberRepository,
            PaymentRepository paymentRepository,
            DietPlanRepository dietPlanRepository,
            FeePackageRepository feepackageRepository
    ) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.paymentRepository = paymentRepository;
        this.dietPlanRepository = dietPlanRepository;
        this.feePackageRepository = feepackageRepository;
    }

    public UserDashboardDTO getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        UserDashboardDTO dto = new UserDashboardDTO();

        dto.setUsername(user.getUsername());

        dto.setJoinDate(member.getJoinDate());
        dto.setStatus(member.getStatus());

        paymentRepository.findTopByMemberIdOrderByPaymentDateDesc(member.getId())
                .ifPresent(p -> {
                    dto.setLastPaymentAmount(BigDecimal.valueOf(p.getAmount()));
                    dto.setNextDueDate(p.getNextDueDate());
                });

        dietPlanRepository.findByMemberId(member.getId())
                .ifPresent(dp -> dto.setDietDescription(dp.getDietDescription()));
        feePackageRepository.findById(member.getPackageId())
                .ifPresent(fp -> dto.setMembershipName(fp.getName()));

        return dto;
    }
}
