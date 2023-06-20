package dreamdiary.quiz.domain.model;

import java.util.List;

public record Choices(
        List<Choice> values
) {
    public Choices {
        if (null == values) throw QuizException.invalidFormat();
        if (values.isEmpty() || 5 < values.size()) throw QuizException.invalidFormat();
    }
}
