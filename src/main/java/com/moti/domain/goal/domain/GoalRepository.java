package com.moti.domain.goal.domain;

import com.moti.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<Goal> findByIdAndUser(Long id, User user);
}
