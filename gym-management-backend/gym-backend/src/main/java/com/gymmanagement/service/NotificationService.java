package com.gymmanagement.service;

import com.gymmanagement.model.Member;
import com.gymmanagement.model.Notification;
import com.gymmanagement.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification send(Member member, String message) {
        Notification notification = new Notification(member, message, LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getForMember(Long memberId) {
        return notificationRepository.findAll().stream()
                .filter(n -> n.getMember().getId().equals(memberId))
                .toList();
    }
}
