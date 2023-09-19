package dreamdiary.nineoclock.quiz.domain

interface QuizAnswerSubmitRepository {
    fun save(quizAnswerSubmit: QuizAnswerSubmit): QuizAnswerSubmit
}
