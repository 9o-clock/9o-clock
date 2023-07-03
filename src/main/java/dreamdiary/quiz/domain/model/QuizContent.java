package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

public record QuizContent(
        String value
) {
    public QuizContent {
        if (!StringUtils.hasText(value)) throw new InvalidQuizFormatException();
    }
}
