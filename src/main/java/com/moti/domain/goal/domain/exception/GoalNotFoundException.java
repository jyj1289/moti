package com.moti.domain.goal.domain.exception;

import com.moti.domain.goal.domain.exception.error.GoalErrorProperty;
import com.moti.shared.error.MotiException;

public class GoalNotFoundException extends MotiException {
    public GoalNotFoundException() { super(GoalErrorProperty.GOAL_NOT_FOUND); }
}
