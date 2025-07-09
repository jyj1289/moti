package com.moti.domain.goal.domain.exception;

import com.moti.domain.goal.domain.exception.error.GoalErrorProperty;
import com.moti.shared.error.MotiException;

public class AlreadySuccessException extends MotiException {
    public AlreadySuccessException() { super(GoalErrorProperty.ALREADY_SUCCESS); }
}
