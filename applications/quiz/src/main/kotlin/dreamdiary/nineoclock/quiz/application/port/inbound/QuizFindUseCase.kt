package dreamdiary.nineoclock.quiz.application.port.inbound

interface QuizFindUseCase {
    fun findQuiz(publicId: String) : QuizQueryResult?
}
