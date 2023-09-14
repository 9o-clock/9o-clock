package dreamdiary.nineoclock.quiz.domain

interface QuizActionAuthorizer {
    fun authorizeQuizCreateAction(creatorSecureId: String) : Boolean
    fun authorizeQuizSubmitAction(submitterSecureId: String) : Boolean
}
