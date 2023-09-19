package dreamdiary.nineoclock.quiz.api

import com.fasterxml.jackson.annotation.JsonFormat
import dreamdiary.nineoclock.quiz.app.usecase.ChoiceQueryResult
import dreamdiary.nineoclock.quiz.app.usecase.QuizFindUseCase
import dreamdiary.nineoclock.quiz.app.usecase.QuizQueryResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
internal class QuizSearchApi(
    private val quizFindUseCase: QuizFindUseCase
) {

    @GetMapping("quizzes/{quizId}")
    fun searchQuiz(@PathVariable(name = "quizId") quizId: String) : QuizSearchResponse? {
        return quizFindUseCase.findQuiz(quizId)?.toResponse()
    }
}

internal data class QuizSearchResponse(
    val quizId: String,
    val title: String,
    val content: String,
    val choices: List<ChoiceResponse>,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val releaseAt: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val answerReleaseAt: LocalDateTime,
)

private fun QuizQueryResult.toResponse(): QuizSearchResponse {
    return QuizSearchResponse(
        quizId = this.publicId,
        title = this.title,
        content = this.content,
        choices = this.choices.map { it.toResponse() },
        releaseAt = this.releaseAt,
        answerReleaseAt = this.answerReleaseAt
    )
}


internal data class ChoiceResponse (
    val choiceId: String,
    val text: String,
    val isAnswer: Boolean,
)

private fun ChoiceQueryResult.toResponse(): ChoiceResponse {
    return ChoiceResponse(
        choiceId = this.publicId,
        text = this.text,
        isAnswer = this.isAnswer
    )
}
