package dreamdiary.quizsubmit.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubmitRepository extends JpaRepository<QuizSubmit, Long> {
    boolean existsQuizSubmitByUserIdAndQuizId(final Long userId, final Long quizId);
}
