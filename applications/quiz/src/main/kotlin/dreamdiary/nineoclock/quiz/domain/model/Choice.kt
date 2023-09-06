package dreamdiary.nineoclock.quiz.domain.model

import dreamdiary.nineoclock.shard.identifier.ChoiceId

internal data class Choice (
    val value: String,
    val isAnswer: Boolean,
    val id: ChoiceId = ChoiceId(),
)
