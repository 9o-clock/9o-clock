package dreamdiary.nineoclock.domain.quiz.model;

import dreamdiary.nineoclock.domain.quiz.model.exception.InvalidQuizFormatException;

import java.io.Serializable;

public record QuizPublicId(
        String value
) implements Serializable {
    public QuizPublicId {
        if (null == value) throw new InvalidQuizFormatException();
    }
}
