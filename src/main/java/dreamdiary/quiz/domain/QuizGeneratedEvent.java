package dreamdiary.quiz.domain;

import dreamdiary.quiz.domain.model.Quiz;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class QuizGeneratedEvent {
    private final Quiz quiz;

    public static QuizGeneratedEvent mapped(final Quiz quiz) {
        return new QuizGeneratedEvent(quiz);
    }
}
