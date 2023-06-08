package dreamdiary.quiz.domain;

public interface QuizEventHandler {
    void handle(final QuizGeneratedEvent event);
}
