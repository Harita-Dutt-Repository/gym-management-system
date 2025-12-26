package com.gymmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gymmanagement.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n.message FROM Notification n WHERE n.member.id = :memberId")
    List<String> findMessagesByMemberId(Long memberId);
}
