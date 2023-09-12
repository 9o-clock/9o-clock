package dreamdiary.nineoclock.quiz.application.port.outbound

import dreamdiary.nineoclock.quiz.domain.QuizAnswerSubmit

internal interface QuizAnswerSubmitOutPort {
    fun save(quizAnswerSubmit: QuizAnswerSubmit): QuizAnswerSubmit
}
