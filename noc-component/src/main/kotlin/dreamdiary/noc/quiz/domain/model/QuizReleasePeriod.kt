package dreamdiary.noc.quiz.domain.model

import java.time.LocalDateTime

class QuizReleasePeriod(
    val openAt: LocalDateTime, // 공개일자
    val answerOpenAt: LocalDateTime // 정답공개일자
) {
    fun isOpen(): Boolean = openAt.isBefore(LocalDateTime.now())
}
