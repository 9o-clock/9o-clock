package dreamdiary.quiz.repository;

import dreamdiary.quiz.domain.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Long, QuizEntity> {

}
