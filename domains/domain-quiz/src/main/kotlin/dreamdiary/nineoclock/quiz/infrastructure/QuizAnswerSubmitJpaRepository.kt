package dreamdiary.nineoclock.quiz.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
private interface QuizAnswerSubmitJpaRepository : JpaRepository<QuizEntity, Long>
