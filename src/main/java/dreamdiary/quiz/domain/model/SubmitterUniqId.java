package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;

import java.io.Serializable;

public record SubmitterUniqId(
        Long value
) implements Serializable {
    public SubmitterUniqId {
        if (null == value) throw new InvalidQuizFormatException();
    }
}
