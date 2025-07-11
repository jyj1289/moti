package com.moti.domain.quiz.domain.exception;

import com.moti.domain.quiz.domain.exception.error.QuizErrorProperty;
import com.moti.shared.error.MotiException;

public class AlreadyQuizSolvedException extends MotiException {
    public AlreadyQuizSolvedException() { super(QuizErrorProperty.ALREADY_QUIZ_SOLVED); }
}
