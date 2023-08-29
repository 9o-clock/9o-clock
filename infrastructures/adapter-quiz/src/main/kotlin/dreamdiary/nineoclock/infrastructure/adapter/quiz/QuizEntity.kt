package dreamdiary.nineoclock.infrastructure.adapter.quiz

import java.time.LocalDateTime

internal class QuizEntity(
    val title: String,
    val content: String,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime,
    val id:Long? = null
)
