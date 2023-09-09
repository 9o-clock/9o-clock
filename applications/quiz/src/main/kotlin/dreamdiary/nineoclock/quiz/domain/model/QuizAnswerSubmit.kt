package dreamdiary.nineoclock.quiz.domain.model

import dreamdiary.nineoclock.shard.identifier.ChoiceId
import dreamdiary.nineoclock.shard.identifier.QuizId

internal data class QuizAnswerSubmit (
    val submitterId: String,
    val quizId: QuizId,
    val choiceId: ChoiceId
)
