package dreamdiary.nineoclock.quiz.application.usecase

interface QuizCreateUseCase {
    fun createQuiz(command: QuizCreateCommand): String
}
