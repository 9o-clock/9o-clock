package dreamdiary.nineoclock.quiz.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface QuizJpaRepository : JpaRepository<QuizEntity, Long> {
    fun findByPublicId(publicId: String): QuizEntity?
}
