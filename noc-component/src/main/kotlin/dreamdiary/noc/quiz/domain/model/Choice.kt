package dreamdiary.noc.quiz.domain.model


data class Choice (
    val value: String,
    val isAnswer: Boolean,
    val id: ChoiceId = ChoiceId(),
)
