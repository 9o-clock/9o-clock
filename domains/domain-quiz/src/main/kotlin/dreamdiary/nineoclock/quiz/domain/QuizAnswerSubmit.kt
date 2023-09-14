package dreamdiary.nineoclock.quiz.domain

import dreamdiary.nineoclock.shard.identifier.ChoiceId
import dreamdiary.nineoclock.shard.identifier.QuizId

data class QuizAnswerSubmit (
    val submitterId: String,
    val quizId: QuizId,
    val choiceId: ChoiceId
)
