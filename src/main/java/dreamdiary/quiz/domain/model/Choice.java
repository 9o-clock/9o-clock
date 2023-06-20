package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record Choice(
        String value
) {
    public Choice {
        if (!StringUtils.hasText(value) || 10 < value.length()) throw QuizException.invalidFormat();
    }
}
