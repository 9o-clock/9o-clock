package dreamdiary.nineoclock.quiz.app.usecase

interface QuizFindUseCase {
    fun findQuiz(publicId: String) : QuizQueryResult?
}
