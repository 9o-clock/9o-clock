package dreamdiary.quiz.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class QuizContent {
    private final String value;

    public QuizContent(final String value) {
        if (!StringUtils.hasText(value)) throw QuizException.InvalidFormat();
        this.value = value;
    }
}
