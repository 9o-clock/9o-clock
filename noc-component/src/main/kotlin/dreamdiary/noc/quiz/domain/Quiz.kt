package dreamdiary.noc.quiz.domain

import dreamdiary.nineoclock.shard.identifier.QuizId

class Quiz(
    val title: QuizTitle,
    val content: QuizContent,
    val choiceGroup: ChoiceGroup,
    val releasePeriod: QuizReleasePeriod,
    val id: QuizId = QuizId()
) {
    fun validateAndToSubmit(submitterId: String, choicePublicId: ChoicePublicId): QuizAnswerSubmit {
        val choice = choiceGroup.findChoiceByPublicId(choicePublicId) ?: throw RuntimeException()
        if (!releasePeriod.isOpen()) throw RuntimeException()

        return QuizAnswerSubmit(
            submitterId = submitterId,
            quizId = id,
            choiceId = choice.id
        )
    }
}
