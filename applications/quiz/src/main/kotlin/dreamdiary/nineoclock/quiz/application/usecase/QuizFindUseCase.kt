package dreamdiary.nineoclock.quiz.application.usecase

interface QuizFindUseCase {
    fun findQuiz(publicId: String) : QuizQueryResult?
}
