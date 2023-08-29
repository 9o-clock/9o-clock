package dreamdiary.nineoclock.application.quiz.usecase

import java.time.LocalDateTime

data class QuizCreateCommand(
    val creatorId: String,
    val title: String,
    val content: String,
    val choices: List<String>,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime
)
