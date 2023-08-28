package dreamdiary.nineoclock.infrastructure.quiz;

import dreamdiary.quiz.domain.model.QuizPublicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface QuizEntityRepository extends JpaRepository<QuizEntity, Long> {
    boolean existsByTitle(final String title);
    boolean existsByPublicId(final QuizPublicId publicId);
    Optional<QuizEntity> findByPublicId(final QuizPublicId publicId);
}
