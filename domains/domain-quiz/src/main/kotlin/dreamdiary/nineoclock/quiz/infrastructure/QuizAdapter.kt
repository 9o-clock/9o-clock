package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.Quiz
import dreamdiary.nineoclock.quiz.domain.QuizRepository
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
import org.springframework.stereotype.Component

@Component
private class QuizAdapter(
    private val quizRepository: QuizJpaRepository
) : QuizRepository {
    override fun save(quiz: Quiz): Quiz {
        quizRepository.save(quiz.toEntity())
        return quiz
    }

    override fun findByPublicId(publicId: QuizPublicId): Quiz? {
        return quizRepository.findByPublicId(publicId.value)?.toModel()
    }
}
