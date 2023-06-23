package dreamdiary.quiz.domain.model;

import java.io.Serializable;

public record SubmitterUniqId(
        Long value
) implements Serializable {
    public SubmitterUniqId {
        if (null == value) throw QuizException.invalidFormat();
    }
}
