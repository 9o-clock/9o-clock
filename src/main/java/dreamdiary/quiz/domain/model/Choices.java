package dreamdiary.quiz.domain.model;

import java.util.List;

public record Choices(
        List<Choice> values
) {
    public Choices {
        if (null == values) throw QuizException.invalidFormat();
        if (0 == values.size() || 4 < values.size()) throw QuizException.invalidFormat();
    }
}
