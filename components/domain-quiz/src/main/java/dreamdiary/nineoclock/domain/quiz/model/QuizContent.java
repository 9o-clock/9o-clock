package dreamdiary.nineoclock.domain.quiz.model;

import dreamdiary.nineoclock.domain.quiz.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

public record QuizContent(
        String value
) {
    public QuizContent {
        if (!StringUtils.hasText(value)) throw new InvalidQuizFormatException();
    }
}
