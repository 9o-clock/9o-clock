package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;

import java.util.List;

public record Choices(
        List<Choice> values
) {
    public Choices {
        if (null == values) throw new InvalidQuizFormatException();
        if (values.isEmpty() || 5 < values.size()) throw new InvalidQuizFormatException();
    }
}
