package dreamdiary.quiz.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuizEntityRepository extends JpaRepository<QuizEntity, Long> {
    boolean existsByTitle(final String title);
    boolean existsByPublicId(final String publicId);
}
