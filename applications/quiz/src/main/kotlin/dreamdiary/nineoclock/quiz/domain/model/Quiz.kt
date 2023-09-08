package dreamdiary.nineoclock.quiz.domain.model

import dreamdiary.nineoclock.shard.identifier.ChoicePublicId
import dreamdiary.nineoclock.shard.identifier.QuizId
import java.time.LocalDateTime

internal class Quiz(
    val title: QuizTitle,
    val content: QuizContent,
    val choiceGroup: ChoiceGroup,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime,
    val id: QuizId = QuizId()
) {
    fun validateAndToSubmit(submitterId: String, choicePublicId: ChoicePublicId): QuizAnswerSubmit {
        val choice = choiceGroup.findChoiceByPublicId(choicePublicId) ?: throw RuntimeException()
        if (releaseAt.isAfter(LocalDateTime.now())) throw RuntimeException()

        return QuizAnswerSubmit(
            submitterId = submitterId,
            quizId = id,
            choiceId = choice.id
        )
    }
}
