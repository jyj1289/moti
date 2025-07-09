package com.moti.domain.goal.domain.exception.error;

import com.moti.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GoalErrorProperty implements ErrorProperty {
    GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 목표를 찾을 수 없습니다."),
    ALREADY_SUCCESS(HttpStatus.CONFLICT, "이미 성공한 목표입니다."),
    ALREADY_FAILED(HttpStatus.CONFLICT, "이미 실패한 목표입니다.");

    private final HttpStatus status;
    private final String message;
}
