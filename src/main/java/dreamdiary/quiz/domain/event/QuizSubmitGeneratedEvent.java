package dreamdiary.quiz.domain.event;

import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizSubmit;

public record QuizSubmitGeneratedEvent(
        QuizSubmit quizSubmit
) {
    public QuizSubmitGeneratedEvent {
        if (null == quizSubmit) throw QuizException.invalidFormat();
    }

    public static QuizSubmitGeneratedEvent mapped(final QuizSubmit quizSubmit) {
        return new QuizSubmitGeneratedEvent(quizSubmit);
    }
}
