package dreamdiary.nineoclock.quiz.domain.model

internal data class ChoiceGroup(
    val values: Collection<Choice>
) {
    init {
        validateChoiceCount()
    }

    private fun validateChoiceCount() {
        if (values.isEmpty() || 5 < values.size) throw IllegalArgumentException()
    }
}
