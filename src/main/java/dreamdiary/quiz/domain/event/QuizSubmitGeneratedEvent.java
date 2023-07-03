package dreamdiary.quiz.domain.event;

import dreamdiary.quiz.domain.model.QuizSubmit;
import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;

public record QuizSubmitGeneratedEvent(
        QuizSubmit quizSubmit
) {
    public QuizSubmitGeneratedEvent {
        if (null == quizSubmit) throw new InvalidQuizFormatException();
    }

    public static QuizSubmitGeneratedEvent mapped(final QuizSubmit quizSubmit) {
        return new QuizSubmitGeneratedEvent(quizSubmit);
    }
}
