package dreamdiary.nineoclock.quiz.domain

import dreamdiary.nineoclock.shard.identifier.QuizPublicId

interface QuizRepository {
    fun save(quiz: Quiz): Quiz
    fun findByPublicId(publicId: QuizPublicId): Quiz?
}
