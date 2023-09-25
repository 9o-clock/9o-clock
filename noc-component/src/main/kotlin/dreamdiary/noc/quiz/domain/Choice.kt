package dreamdiary.noc.quiz.domain

import dreamdiary.nineoclock.shard.identifier.ChoiceId


data class Choice (
    val value: String,
    val isAnswer: Boolean,
    val id: ChoiceId = ChoiceId(),
)
