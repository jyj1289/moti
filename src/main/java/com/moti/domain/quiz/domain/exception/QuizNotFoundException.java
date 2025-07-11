package com.moti.domain.quiz.domain.exception;

import com.moti.domain.quiz.domain.exception.error.QuizErrorProperty;
import com.moti.shared.error.MotiException;

public class QuizNotFoundException extends MotiException {
    public QuizNotFoundException() { super(QuizErrorProperty.QUIZ_NOT_FOUND); }
}
