package dreamdiary.nineoclock.quiz.domain.service

interface QuizActionAuthorizer {
    fun authorizeQuizCreateAction(creatorId: String) : Boolean
}
