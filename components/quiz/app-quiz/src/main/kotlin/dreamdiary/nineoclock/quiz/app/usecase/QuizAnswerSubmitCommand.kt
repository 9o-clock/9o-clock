package dreamdiary.nineoclock.quiz.app.usecase

import jakarta.validation.constraints.NotBlank

data class QuizAnswerSubmitCommand(
    @field:NotBlank val submitterSecureId: String, // userSecureId
    @field:NotBlank val quizPublicId: String,
    @field:NotBlank val choicePublicId: String,
)
