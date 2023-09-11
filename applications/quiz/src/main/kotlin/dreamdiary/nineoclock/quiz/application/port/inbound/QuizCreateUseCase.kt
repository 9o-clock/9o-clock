package dreamdiary.nineoclock.quiz.application.port.inbound

interface QuizCreateUseCase {
    fun createQuiz(command: QuizCreateCommand): String
}
