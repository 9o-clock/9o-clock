package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

public record QuizTitle(
        String value
) {
    public QuizTitle {
        if (!StringUtils.hasText(value)) throw new InvalidQuizFormatException();
    }
}
