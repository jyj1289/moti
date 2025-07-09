package com.moti.domain.auth.domain.exception;

import com.moti.domain.auth.domain.exception.error.AuthErrorProperty;
import com.moti.shared.error.MotiException;

public class EmptyTokenException extends MotiException {
    public EmptyTokenException() { super(AuthErrorProperty.EMPTY_TOKEN); }
}
