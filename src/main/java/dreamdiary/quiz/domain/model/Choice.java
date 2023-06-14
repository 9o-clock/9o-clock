package dreamdiary.quiz.domain.model;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Choice {
    private final String text;

    public Choice(final String text) {
        if (!StringUtils.hasText(text) || 10 < text.length()) throw QuizException.invalidFormat();
        this.text = text;
    }
}
