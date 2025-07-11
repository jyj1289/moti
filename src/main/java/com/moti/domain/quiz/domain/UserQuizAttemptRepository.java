package com.moti.domain.quiz.domain;

import com.moti.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserQuizAttemptRepository extends JpaRepository<UserQuizAttempt,Long> {
    boolean existsByUserAndCreatedAtAfter(User user, LocalDateTime createdAt);
    List<UserQuizAttempt> findByUser(User user);
}
