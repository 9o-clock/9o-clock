package dreamdiary.quiz.domain;

import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizTitle;

public interface QuizRepository {
    boolean isTitleAlreadyExists(final QuizTitle title);
    Quiz store(final Quiz quiz);
}
