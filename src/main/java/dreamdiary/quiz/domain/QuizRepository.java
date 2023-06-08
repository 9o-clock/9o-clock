package dreamdiary.quiz.domain;

public interface QuizRepository {
    boolean isTitleAlreadyExists(final QuizTitle title);
    Quiz store(final Quiz quiz);
}
