package dreamdiary.nineoclock.quiz.application.usecase

import java.time.LocalDateTime

data class QuizQueryResult(
    val publicId: String,
    val title: String,
    val content: String,
    val choices: List<ChoiceQueryResult>,
    val releaseAt: LocalDateTime,
    val answerReleaseAt: LocalDateTime,
)

data class ChoiceQueryResult(
    val publicId: String,
    val text: String,
    val isAnswer: Boolean,
)
