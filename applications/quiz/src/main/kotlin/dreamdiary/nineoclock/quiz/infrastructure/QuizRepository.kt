package dreamdiary.nineoclock.quiz.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

internal interface QuizRepository : JpaRepository<QuizEntity, Long> {
    fun findByPublicId(publicId: String): QuizEntity?
}
