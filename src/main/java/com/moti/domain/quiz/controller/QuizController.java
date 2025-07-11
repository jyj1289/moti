package com.moti.domain.quiz.controller;

import com.moti.domain.quiz.controller.dto.response.QuizResponse;
import com.moti.domain.quiz.service.QuizService;
import com.moti.domain.user.domain.User;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
