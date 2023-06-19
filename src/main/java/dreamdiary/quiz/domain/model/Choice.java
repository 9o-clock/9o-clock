package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record Choice(
        String text
) {
    public Choice {
        if (!StringUtils.hasText(text) || 10 < text.length()) throw QuizException.invalidFormat();
    }
}
