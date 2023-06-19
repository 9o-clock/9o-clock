package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizContent(
        String value
) {
    public QuizContent {
        if (!StringUtils.hasText(value)) throw QuizException.invalidFormat();
    }
}
