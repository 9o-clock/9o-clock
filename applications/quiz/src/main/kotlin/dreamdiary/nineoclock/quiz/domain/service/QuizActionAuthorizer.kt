package dreamdiary.nineoclock.quiz.domain.service

internal interface QuizActionAuthorizer {
    fun authorizeQuizCreateAction(creatorSecureId: String) : Boolean
    fun authorizeQuizSubmitAction(submitterSecureId: String) : Boolean
}
