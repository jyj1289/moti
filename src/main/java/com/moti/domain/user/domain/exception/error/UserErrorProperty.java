package com.moti.domain.user.domain.exception.error;

import com.moti.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorProperty implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
