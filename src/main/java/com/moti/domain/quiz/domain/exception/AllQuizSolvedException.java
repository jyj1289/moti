package com.moti.domain.quiz.domain.exception;

import com.moti.domain.quiz.domain.exception.error.QuizErrorProperty;
import com.moti.shared.error.MotiException;

public class AllQuizSolvedException extends MotiException {
    public AllQuizSolvedException() { super(QuizErrorProperty.ALL_QUIZ_SOLVED); }
}
