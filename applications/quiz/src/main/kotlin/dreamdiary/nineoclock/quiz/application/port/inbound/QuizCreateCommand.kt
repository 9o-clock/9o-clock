package dreamdiary.nineoclock.quiz.application.port.inbound

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class QuizCreateCommand(
    @field:NotBlank val creatorId: String, // userSecureId
    @field:NotBlank val title: String,
    @field:NotBlank val content: String,
    @field:NotEmpty val choices: List<ChoiceInfoDto>,
    @field:NotNull val releaseAt: LocalDateTime,
    @field:NotNull val answerReleaseAt: LocalDateTime
)

data class ChoiceInfoDto(
    @field:NotBlank val value: String,
    @field:NotNull val isAnswer: Boolean
)
