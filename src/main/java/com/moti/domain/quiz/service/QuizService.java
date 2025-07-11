package com.moti.domain.quiz.service;

import com.moti.domain.quiz.controller.dto.response.QuizResponse;
import com.moti.domain.quiz.controller.dto.response.QuizSolvedResponse;
import com.moti.domain.quiz.controller.dto.response.UserQuizAttemptResponse;
import com.moti.domain.quiz.domain.Quiz;
import com.moti.domain.quiz.domain.QuizRepository;
import com.moti.domain.quiz.domain.UserQuizAttempt;
import com.moti.domain.quiz.domain.UserQuizAttemptRepository;
import com.moti.domain.quiz.domain.exception.AlreadyQuizSolvedException;
import com.moti.domain.quiz.domain.exception.QuizNotFoundException;
import com.moti.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserQuizAttemptRepository attemptRepository;

    public QuizResponse getQuiz(User user) {
        validate(user);

        LocalDate startDate = LocalDate.of(2025, 7, 10);
        LocalDate toDay = LocalDate.now();
        Long index = ChronoUnit.DAYS.between(startDate, toDay) + 1;

        Quiz quiz = quizRepository.findById(index)
                .orElseThrow(QuizNotFoundException::new);
        return new QuizResponse(quiz);
    }

    @Transactional
    public QuizSolvedResponse solveQuiz(User user, Long id, Long answer) {
        validate(user);

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(QuizNotFoundException::new);

        boolean isSolved =  quiz.getCorrectAnswer().equals(answer);
        if (isSolved) {
            user.increasePoint(100);
        }
        UserQuizAttempt attempt = new UserQuizAttempt(isSolved, answer, quiz.getCorrectAnswer(), user, quiz);
        attemptRepository.save(attempt);

        return new QuizSolvedResponse(isSolved, quiz.getCorrectAnswer());
    }

    public List<UserQuizAttemptResponse> getUserQuizAttempts(User user) {
        return attemptRepository.findByUser(user).stream()
                .map(UserQuizAttemptResponse::new)
                .toList();
    }

    private void validate(User user) {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        if (attemptRepository.existsByUserAndCreatedAtAfter(user, now)) {
            throw new AlreadyQuizSolvedException();
        }
    }
}
