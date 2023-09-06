package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.model.Quiz
import dreamdiary.nineoclock.quiz.domain.outbound.QuizOutPort
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
import org.springframework.stereotype.Component

@Component
private class QuizAdapter(
    private val quizRepository: QuizRepository
) : QuizOutPort {
    override fun save(quiz: Quiz): Quiz {
        quizRepository.save(quiz.toEntity())
        return quiz
    }

    override fun findByPublicId(publicId: QuizPublicId): Quiz? {
        return quizRepository.findByPublicId(publicId.value)?.toModel()
    }
}
