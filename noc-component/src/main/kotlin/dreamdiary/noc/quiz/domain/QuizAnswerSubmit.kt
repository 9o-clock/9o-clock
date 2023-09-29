package dreamdiary.noc.quiz.domain

import dreamdiary.nineoclock.shard.identifier.QuizId

data class QuizAnswerSubmit (
    val submitterId: String,
    val quizId: QuizId,
    val choiceId: ChoiceId
)
