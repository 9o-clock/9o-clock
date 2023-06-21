package dreamdiary.quiz.domain;

import dreamdiary.quiz.domain.event.QuizGeneratedEvent;
import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;

public interface QuizEventHandler {
    void handle(final QuizGeneratedEvent event);

    void handle(final QuizSubmitGeneratedEvent event);
}
