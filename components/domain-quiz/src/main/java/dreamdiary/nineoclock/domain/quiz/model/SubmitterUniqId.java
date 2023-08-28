package dreamdiary.nineoclock.domain.quiz.model;

import dreamdiary.nineoclock.domain.quiz.model.exception.InvalidQuizFormatException;

import java.io.Serializable;

public record SubmitterUniqId(
        Long value
) implements Serializable {
    public SubmitterUniqId {
        if (null == value) throw new InvalidQuizFormatException();
    }
}
