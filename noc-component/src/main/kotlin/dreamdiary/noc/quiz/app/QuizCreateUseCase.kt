package dreamdiary.noc.quiz.app

interface QuizCreateUseCase {
    fun createQuiz(command: QuizCreateCommand): QuizCreateResult
}
