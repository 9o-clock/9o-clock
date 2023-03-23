package dreamdiary.quiz.repository;

import dreamdiary.quiz.domain.entity.QuizEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Integer> {
  QuizEntity findByReleasedDate(LocalDateTime releasedDate);
}
