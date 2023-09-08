package dreamdiary.nineoclock.quiz.application.usecase

interface QuizAnswerSubmitUseCase {
    fun submitQuizAnswer(command: QuizAnswerSubmitCommand)
}
