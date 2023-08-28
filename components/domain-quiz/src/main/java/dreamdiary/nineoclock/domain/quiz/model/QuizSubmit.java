package dreamdiary.nineoclock.domain.quiz.model;

import dreamdiary.nineoclock.domain.quiz.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

public record QuizSubmit(
        QuizPublicId quizPublicId,
        SubmitterUniqId submitterUniqId,
        String choicePublicId
) {
    public QuizSubmit {
        if (null == quizPublicId) throw new InvalidQuizFormatException();
        if (null == submitterUniqId) throw new InvalidQuizFormatException();
        if (!StringUtils.hasText(choicePublicId)) throw new InvalidQuizFormatException();
    }
}
