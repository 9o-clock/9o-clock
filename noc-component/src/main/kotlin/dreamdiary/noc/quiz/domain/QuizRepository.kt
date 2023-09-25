package dreamdiary.noc.quiz.domain

import dreamdiary.nineoclock.shard.identifier.QuizPublicId

interface QuizRepository {
    fun save(quiz: Quiz): Quiz
    fun findByPublicId(publicId: QuizPublicId): Quiz?
}
