package dreamdiary.quiz.domain.model;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class QuizTitle {
    private final String value;

    public QuizTitle(final String title) {
        if (!StringUtils.hasText(title)) throw QuizException.invalidFormat();
        this.value = title;
    }
}
