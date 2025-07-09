package com.moti.domain.auth.domain.exception;

import com.moti.domain.auth.domain.exception.error.AuthErrorProperty;
import com.moti.shared.error.MotiException;

public class ExpiredTokenException extends MotiException {
    public ExpiredTokenException() { super(AuthErrorProperty.EXPIRED_TOKEN); }
}
