package dreamdiary.nineoclock.quiz.app.usecase

interface QuizCreateUseCase {
    fun createQuiz(command: QuizCreateCommand): String
}
