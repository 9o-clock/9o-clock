package dreamdiary.nineoclock.quiz.application.port.inbound

interface QuizAnswerSubmitUseCase {
    fun submitQuizAnswer(command: QuizAnswerSubmitCommand)
}
