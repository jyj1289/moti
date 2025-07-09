package com.moti.domain.goal.domain.exception;

import com.moti.domain.goal.domain.exception.error.GoalErrorProperty;
import com.moti.shared.error.MotiException;

public class AlreadyFailedException extends MotiException {
    public AlreadyFailedException() { super(GoalErrorProperty.ALREADY_FAILED); }
}
