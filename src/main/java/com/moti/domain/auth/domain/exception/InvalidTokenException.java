package com.moti.domain.auth.domain.exception;

import com.moti.domain.auth.domain.exception.error.AuthErrorProperty;
import com.moti.shared.error.MotiException;

public class InvalidTokenException extends MotiException {
    public InvalidTokenException() { super(AuthErrorProperty.INVALID_TOKEN); }
}
