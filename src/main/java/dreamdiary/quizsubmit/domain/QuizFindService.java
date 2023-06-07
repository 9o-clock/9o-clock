package dreamdiary.quizsubmit.domain;

import java.util.Optional;

public interface QuizFindService {
    Optional<QuizFindResponse> findQuiz(final Long quizId);
}
