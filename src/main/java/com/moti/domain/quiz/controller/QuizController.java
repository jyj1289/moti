package com.moti.domain.quiz.controller;

import com.moti.domain.quiz.controller.dto.response.QuizResponse;
import com.moti.domain.quiz.controller.dto.response.QuizSolvedResponse;
import com.moti.domain.quiz.controller.dto.response.UserQuizAttemptResponse;
import com.moti.domain.quiz.domain.UserQuizAttempt;
import com.moti.domain.quiz.service.QuizService;
import com.moti.domain.user.domain.User;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/quizzes")
@RestController
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<CommonResponse<QuizResponse>> getQuiz(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        quizService.getQuiz(user)
                ));
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<CommonResponse<QuizSolvedResponse>> solveQuiz(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody Long answer
    ) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        quizService.solveQuiz(user, id, answer)
                ));
    }

    @GetMapping("/attempts")
    public ResponseEntity<CommonResponse<List<UserQuizAttemptResponse>>> getAttemptedQuiz(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        quizService.getUserQuizAttempts(user)
                ));
    }
}
