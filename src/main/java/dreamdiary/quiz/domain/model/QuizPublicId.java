package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;

import java.io.Serializable;

public record QuizPublicId(
        String value
) implements Serializable {
    public QuizPublicId {
        if (null == value) throw new InvalidQuizFormatException();
    }
}
