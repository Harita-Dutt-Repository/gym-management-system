package com.gymmanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.model.FeePackage;
import com.gymmanagement.model.Member;
import com.gymmanagement.model.User;
import com.gymmanagement.service.FeePackageService;
import com.gymmanagement.service.MemberService;
import com.gymmanagement.service.UserService;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;
    private final UserService userService;
    private final FeePackageService feePackageService;

    public MemberController(
            MemberService memberService,
            UserService userService,
            FeePackageService feePackageService
    ) {
        this.memberService = memberService;
        this.userService = userService;
        this.feePackageService = feePackageService;
    }

    // ✅ Get all members
    @GetMapping
    public List<Member> getAll() {
        return memberService.getAllMembers();
    }

    // ✅ Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return memberService.getMember(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create new member + linked user
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Map<String, String> request) {

        // 1️⃣ Create User
        User user = new User(
                request.get("username"),
                request.get("password"),
                "MEMBER"
        );
        user = userService.saveUser(user);

        // 2️⃣ Get Fee Package
        Long packageId = Long.parseLong(request.get("packageId"));
        FeePackage feePackage = feePackageService
                .getById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        // 3️⃣ Create Member
        Member member = new Member();
        member.setUserId(user.getId());
        member.setPackageId(feePackage.getId());
        member.setFullName(request.get("fullName"));
        member.setPhone(request.get("phone"));
        member.setJoinDate(LocalDate.parse(request.get("joinDate")));
        member.setStatus(request.get("status"));

        return ResponseEntity.ok(memberService.save(member));
    }

    // ✅ Update member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @RequestBody Member updated
    ) {
        Optional<Member> existingOpt = memberService.getMember(id);

        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member existing = existingOpt.get();
        existing.setFullName(updated.getFullName());
        existing.setPhone(updated.getPhone());
        existing.setStatus(updated.getStatus());

        return ResponseEntity.ok(memberService.save(existing));
    }

    // ✅ Delete member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.ok("Member deleted");
    }
}
