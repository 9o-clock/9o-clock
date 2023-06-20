package dreamdiary.quiz.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubmitRepository extends JpaRepository<QuizSubmitEntity, Long> {
}
