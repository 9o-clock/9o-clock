package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizTitle(
        String value
) {
    public QuizTitle {
        if (!StringUtils.hasText(value)) throw QuizException.invalidFormat();
    }
}
