package dreamdiary.noc.quiz.domain

interface QuizAnswerSubmitRepository {
    fun save(quizAnswerSubmit: QuizAnswerSubmit): QuizAnswerSubmit
}
