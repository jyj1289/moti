package com.moti.domain.quiz.controller.dto.response;

import com.moti.domain.quiz.domain.Answer;
import com.moti.domain.quiz.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class QuizResponse {

    private Long id;
    private String question;
    private List<AnswerResponse> answers;

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.question = quiz.getQuestion();
        this.answers = quiz.getAnswerList().stream()
                .map(AnswerResponse::new)
                .toList();
    }
}
