package dreamdiary.nineoclock.quiz.domain

import dreamdiary.nineoclock.shard.identifier.ChoiceId

internal data class Choice (
    val value: String,
    val isAnswer: Boolean,
    val id: ChoiceId = ChoiceId(),
)
