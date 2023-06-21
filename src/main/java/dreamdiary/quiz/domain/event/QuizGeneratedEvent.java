package dreamdiary.quiz.domain.event;

import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;

public record QuizGeneratedEvent(
        Quiz quiz
) {
    public QuizGeneratedEvent {
        if (null == quiz) throw QuizException.invalidFormat();
    }

    public static QuizGeneratedEvent mapped(final Quiz quiz) {
        return new QuizGeneratedEvent(quiz);
    }
}
