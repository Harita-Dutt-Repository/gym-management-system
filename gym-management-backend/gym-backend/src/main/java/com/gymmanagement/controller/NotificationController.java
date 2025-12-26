package com.gymmanagement.controller;

import com.gymmanagement.model.Member;
import com.gymmanagement.model.Notification;
import com.gymmanagement.service.MemberService;
import com.gymmanagement.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;
    private final MemberService memberService;

    public NotificationController(NotificationService notificationService, MemberService memberService) {
        this.notificationService = notificationService;
        this.memberService = memberService;
    }

    // Send a notification
    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody Map<String, String> request) {

        Long memberId = Long.parseLong(request.get("memberId"));
        String message = request.get("message");

        Optional<Member> member = memberService.getMember(memberId);

        if (member.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid member id");
        }

        Notification n = notificationService.send(member.get(), message);

        return ResponseEntity.ok(n);
    }

    // Get notifications for a member
    @GetMapping("/member/{memberId}")
    public List<Notification> getForMember(@PathVariable Long memberId) {
        return notificationService.getForMember(memberId);
    }
}
