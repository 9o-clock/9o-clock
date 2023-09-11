package dreamdiary.nineoclock.quiz.application.port.inbound

import jakarta.validation.constraints.NotBlank

data class QuizAnswerSubmitCommand(
    @field:NotBlank val submitterSecureId: String, // userSecureId
    @field:NotBlank val quizPublicId: String,
    @field:NotBlank val choicePublicId: String,
)
