package com.moti.domain.quiz.domain;

import com.moti.domain.user.domain.User;
import com.moti.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserQuizAttempt extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quiz_attempt_id")
    private Long id;

    @Column(nullable = false)
    private boolean isSolved;

    @Column(nullable = false)
    private Long answer;

    @Column(nullable = false)
    private Long correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name  = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "quiz_id")
    private Quiz quiz;

    public UserQuizAttempt(boolean isSolved, Long answer, Long correctAnswer, User user, Quiz quiz) {
        this.isSolved = isSolved;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.user = user;
        this.quiz = quiz;
    }
}
