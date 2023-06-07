package dreamdiary.quiz.domain;

public interface QuizRepository {
    boolean isTitleAlreadyExists(final QuizTitle title);
}
