package dreamdiary.nineoclock.quiz.application.port.outbound

import dreamdiary.nineoclock.quiz.domain.model.Quiz
import dreamdiary.nineoclock.shard.identifier.QuizPublicId

internal interface QuizOutPort {
    fun save(quiz: Quiz): Quiz
    fun findByPublicId(publicId: QuizPublicId): Quiz?
}
