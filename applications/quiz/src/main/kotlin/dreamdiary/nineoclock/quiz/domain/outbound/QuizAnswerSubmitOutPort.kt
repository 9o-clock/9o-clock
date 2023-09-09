package dreamdiary.nineoclock.quiz.domain.outbound

import dreamdiary.nineoclock.quiz.domain.model.QuizAnswerSubmit

internal interface QuizAnswerSubmitOutPort {
    fun save(quizAnswerSubmit: QuizAnswerSubmit): QuizAnswerSubmit
}
