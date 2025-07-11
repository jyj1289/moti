package com.moti.domain.quiz.controller.dto.response;

import com.moti.domain.quiz.domain.UserQuizAttempt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserQuizAttemptResponse {

    private final boolean isSolved;
    private final Long userAnswer;
    private final Long correctAnswer;
    private final QuizResponse quiz;

    public UserQuizAttemptResponse(UserQuizAttempt attempt) {
        this.isSolved = attempt.isSolved();
        this.userAnswer = attempt.getAnswer();
        this.correctAnswer = attempt.getCorrectAnswer();
        this.quiz = new QuizResponse(attempt.getQuiz());
    }
}
