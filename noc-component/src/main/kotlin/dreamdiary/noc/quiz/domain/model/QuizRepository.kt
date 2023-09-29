package dreamdiary.noc.quiz.domain.model

interface QuizRepository {
    fun save(quiz: Quiz): Quiz
    fun findByPublicId(publicId: QuizPublicId): Quiz?
}
