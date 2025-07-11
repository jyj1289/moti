package com.moti.domain.quiz.controller.dto.response;

import com.moti.domain.quiz.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerResponse {

    private final Long answerNumber;
    private final String answer;

    public AnswerResponse(Answer answer) {
        this.answerNumber = answer.getAnswerNumber();
        this.answer = answer.getAnswer();
    }
}
