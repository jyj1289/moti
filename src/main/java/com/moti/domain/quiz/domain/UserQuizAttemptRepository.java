package com.moti.domain.quiz.domain;

import com.moti.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserQuizAttemptRepository extends JpaRepository<UserQuizAttempt,Long> {
    int countByUser(User user);
    boolean existsByUserAndCreatedAtAfter(User user, LocalDateTime createdAt);
    boolean existsByUserAndQuiz(User user, Quiz quiz);
}
