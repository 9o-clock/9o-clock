package dreamdiary.noc.quiz.domain


data class Choice (
    val value: String,
    val isAnswer: Boolean,
    val id: ChoiceId = ChoiceId(),
)
