package dreamdiary.nineoclock.quiz.domain.model

import dreamdiary.nineoclock.shard.identifier.QuizId
import java.time.LocalDateTime

internal class Quiz(
    val title: QuizTitle,
    val content: QuizContent,
    val choiceGroup: ChoiceGroup,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime,
    val id: QuizId = QuizId()
)
