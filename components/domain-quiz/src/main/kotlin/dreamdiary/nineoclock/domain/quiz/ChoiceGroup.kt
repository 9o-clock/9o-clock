package dreamdiary.nineoclock.domain.quiz

data class ChoiceGroup(
    val values: Collection<Choice>
) {
    init {
        validateChoiceCount()
    }

    private fun validateChoiceCount() {
        if (values.isEmpty() || 5 < values.size) throw IllegalArgumentException()
    }
}


