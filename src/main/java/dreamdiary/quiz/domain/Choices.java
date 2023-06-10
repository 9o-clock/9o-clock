package dreamdiary.quiz.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Choices {
    private final List<Choice> values;

    public Choices(final Choice... choices) {
        if (null == choices) throw QuizException.invalidFormat();
        if (0 >= choices.length || 4 < choices.length) throw QuizException.invalidFormat();
        this.values = Arrays.stream(choices).toList();
    }
}
