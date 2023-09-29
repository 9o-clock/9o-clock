package dreamdiary.noc.quiz.domain

import dreamdiary.noc.quiz.domain.model.QuizAnswerSubmit

interface QuizAnswerSubmitRepository {
    fun save(quizAnswerSubmit: QuizAnswerSubmit): QuizAnswerSubmit
}
