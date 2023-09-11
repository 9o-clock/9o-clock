package dreamdiary.nineoclock.quiz.application.service

import dreamdiary.nineoclock.quiz.application.port.inbound.QuizAnswerSubmitCommand
import dreamdiary.nineoclock.quiz.helper.QuizTestHelper
import dreamdiary.nineoclock.quiz.helper.any
import dreamdiary.nineoclock.quiz.helper.capture
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.*

class QuizAnswerSubmitServiceTest : QuizTestHelper() {
    @InjectMocks
    internal lateinit var quizAnswerSubmitService: QuizAnswerAnswerSubmitService

    @DisplayName("입력 받은 정보를 검증 및 예외처리 합니다.")
    @Test
    fun submitQuizAnswer_validate_command_and_throw() {
        BDDMockito.given(mockValidator.validate<QuizAnswerSubmitCommand>(any())).willThrow(RuntimeException())

        val exception = catchThrowableOfType({
            quizAnswerSubmitService.submitQuizAnswer(anQuizAnswerSubmitCommand())
        }, RuntimeException::class.java)

        verify(mockValidator, times(1)).validate(capture(quizAnswerSubmitCommandCaptor))
        assertThat(exception).isNotNull()
    }

    @DisplayName("퀴즈 정답 제출자에게 제출 권한이 있는지 검사하고 아니라면 예외처리합니다.")
    @Test
    fun submitQuizAnswer_authorize_submitter_or_throw() {
        BDDMockito.given(mockQuizActionAuthorizer.authorizeQuizSubmitAction(any())).willReturn(false)

        val exception = catchThrowableOfType({
            quizAnswerSubmitService.submitQuizAnswer(anQuizAnswerSubmitCommand())
        }, RuntimeException::class.java)

        assertThat(exception).isNotNull()
    }

    @DisplayName("전달받은 publicId 기준으로 퀴즈 객체를 찾고 없다면 예외처리합니다.")
    @Test
    fun submitQuizAnswer_find_quiz_or_throw() {
        BDDMockito.given(mockQuizOutPort.findByPublicId(any())).willReturn(null)

        val exception = catchThrowableOfType({
            quizAnswerSubmitService.submitQuizAnswer(anQuizAnswerSubmitCommand())
        }, RuntimeException::class.java)

        verify(mockQuizOutPort, times(1)).findByPublicId(capture(quizPublicIdCaptor))
        assertThat(exception).isNotNull()
    }

    @DisplayName("퀴즈 정답을 제출(저장)합니다.")
    @Test
    fun submitQuizAnswer_save() {
        val givenChoice = anChoice()
        val givenQuiz = customQuiz(mutableListOf(givenChoice))
        val givenCommand = QuizAnswerSubmitCommand(
            submitterSecureId = "",
            quizPublicId = givenQuiz.id.publicId.value,
            choicePublicId = givenChoice.id.publicId.value
        )
        BDDMockito.given(mockQuizOutPort.findByPublicId(any())).willReturn(givenQuiz)

        quizAnswerSubmitService.submitQuizAnswer(givenCommand)

        verify(mockQuizAnswerSubmitOutPort, times(1)).save(capture(quizAnswerSubmitCaptor))
    }
}
