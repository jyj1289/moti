package com.moti.domain.user.domain.exception;

import com.moti.domain.user.domain.exception.error.UserErrorProperty;
import com.moti.shared.error.MotiException;

public class UserNotFoundException extends MotiException {
    public UserNotFoundException() { super(UserErrorProperty.USER_NOT_FOUND); }
}
