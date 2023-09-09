package dreamdiary.nineoclock.quiz.command

import com.fasterxml.jackson.annotation.JsonFormat
import dreamdiary.nineoclock.quiz.application.usecase.ChoiceInfoDto
import dreamdiary.nineoclock.quiz.application.usecase.QuizCreateCommand
import dreamdiary.nineoclock.quiz.application.usecase.QuizCreateUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
internal class QuizCreateApi(
    private val quizCreateUseCase: QuizCreateUseCase
) {
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("quizzes")
    internal fun createQuiz(@RequestBody request: QuizCreateRequest): QuizCreateResponse {
        val command = QuizCreateCommand(
            creatorId = "testId",
            title = request.title,
            content = request.content,
            choices = request.choices.map { it.toDto() },
            releaseAt = request.releaseAt,
            answerReleaseAt = request.answerReleaseAt,
        )
        val publicId = quizCreateUseCase.createQuiz(command)
        return QuizCreateResponse(
            id = publicId
        )
    }
}

internal data class QuizCreateRequest(
    val title: String,
    val content: String,
    val choices: List<ChoiceInfo>,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val releaseAt: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val answerReleaseAt: LocalDateTime
)

internal data class QuizCreateResponse(
    val id: String
)

internal data class ChoiceInfo(
    val value: String,
    val isAnswer: Boolean
)

internal fun ChoiceInfo.toDto(): ChoiceInfoDto {
    return ChoiceInfoDto(
        value = this.value,
        isAnswer = this.isAnswer
    )
}
