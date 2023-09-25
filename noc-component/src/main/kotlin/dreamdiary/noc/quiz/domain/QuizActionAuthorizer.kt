package dreamdiary.noc.quiz.domain

interface QuizActionAuthorizer {
    fun authorizeQuizCreateAction(creatorSecureId: String) : Boolean
    fun authorizeQuizSubmitAction(submitterSecureId: String) : QuizAuthorizeQuizSubmitActionResult
}
