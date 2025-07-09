package com.moti.shared.error;

import lombok.Getter;

@Getter
public abstract class MotiException extends RuntimeException {

    private final ErrorProperty errorProperty;

    public MotiException(ErrorProperty errorProperty) {
        super(errorProperty.getMessage());
        this.errorProperty = errorProperty;
    }
}
