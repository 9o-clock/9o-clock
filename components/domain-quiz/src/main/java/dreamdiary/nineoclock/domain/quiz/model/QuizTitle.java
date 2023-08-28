package dreamdiary.nineoclock.domain.quiz.model;

import dreamdiary.nineoclock.domain.quiz.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

public record QuizTitle(
        String value
) {
    public QuizTitle {
        if (!StringUtils.hasText(value)) throw new InvalidQuizFormatException();
    }
}
