package dreamdiary.nineoclock.domain.quiz

import java.time.LocalDateTime

class Quiz(
    val title: QuizTitle,
    val content: QuizContent,
    val choiceGroup:ChoiceGroup,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime
)
