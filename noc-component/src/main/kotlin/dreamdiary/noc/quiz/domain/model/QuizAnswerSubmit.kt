package dreamdiary.noc.quiz.domain.model

data class QuizAnswerSubmit (
    val submitterId: String,
    val quizId: QuizId,
    val choiceId: ChoiceId
)
