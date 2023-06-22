package dreamdiary.quiz.domain.model;

import java.io.Serializable;

public record QuizPublicId(
        String value
) implements Serializable {
    public QuizPublicId {
        if (null == value) throw QuizException.invalidFormat();
    }
}
