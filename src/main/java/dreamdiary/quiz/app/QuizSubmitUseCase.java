package dreamdiary.quiz.app;

public interface QuizSubmitUseCase {
    void submitQuiz(final String quizPublicId, QuizSubmitRequest request);
}
