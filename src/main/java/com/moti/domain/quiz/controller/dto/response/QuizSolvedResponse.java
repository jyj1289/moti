package com.moti.domain.quiz.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizSolvedResponse {

    private final boolean isSolved;
    private final Long correctAnswer;
}
