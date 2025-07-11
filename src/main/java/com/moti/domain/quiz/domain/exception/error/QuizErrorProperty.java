package com.moti.domain.quiz.domain.exception.error;

import com.moti.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum QuizErrorProperty implements ErrorProperty {
    ALL_QUIZ_SOLVED(HttpStatus.CONFLICT, "이미 모든 퀴즈를 다 해결하였습니다."),
    ALREADY_QUIZ_SOLVED(HttpStatus.CONFLICT, "퀴즈는 하루에 한 번만 풀 수 있습니다."),
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 퀴즈를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
