package dreamdiary.nineoclock.quiz.application.service

import dreamdiary.nineoclock.quiz.application.usecase.QuizCreateCommand
import dreamdiary.nineoclock.quiz.helper.QuizTestHelper
import dreamdiary.nineoclock.quiz.helper.any
import dreamdiary.nineoclock.quiz.helper.capture
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.Mockito.*

class QuizCreateServiceTest : QuizTestHelper() {
    @InjectMocks
    internal lateinit var quizCreateService: QuizCreateService

    @DisplayName("입력 받은 정보를 검증 및 예외처리 합니다.")
    @Test
    fun createQuiz_validate_command_and_throw() {
        BDDMockito.given(mockValidator.validate<QuizCreateCommand>(any())).willThrow(RuntimeException())
        val givenCommand = anQuizCreateCommand()

        val exception = catchThrowableOfType({
            quizCreateService.createQuiz(givenCommand)
        }, RuntimeException::class.java)

        verify(mockValidator, times(1)).validate(capture(quizCreateCommandCaptor))
        assertThat(exception).isNotNull()
    }

    @DisplayName("요청자에게 퀴즈 [생성] 권한이 있는지 검사 및 예외처리 합니다.")
    @Test
    fun createQuiz_check_authorize_quiz_create_action_and_throw() {
        BDDMockito.given(mockQuizActionAuthorizer.authorizeQuizCreateAction(any())).willReturn(false)
        val givenCommand = anQuizCreateCommand()

        val exception = catchThrowableOfType({
            quizCreateService.createQuiz(givenCommand)
        }, RuntimeException::class.java)

        verify(mockQuizActionAuthorizer, times(1)).authorizeQuizCreateAction(capture(creatorIdCaptor))
        assertThat(exception).isNotNull()
        assertThat(creatorIdCaptor.value).isEqualTo(givenCommand.creatorId)
    }

    @DisplayName("퀴즈를 저장합니다.")
    @Test
    fun createQuiz_save() {
        val givenCommand = anQuizCreateCommand()

        quizCreateService.createQuiz(givenCommand)

        verify(mockQuizOutPort, times(1)).save(capture(quizCaptor))
    }

    @DisplayName("퀴즈 생성 결과를 반환합니다.")
    @Test
    fun createQuiz_return_value() {
        val givenCommand = anQuizCreateCommand()

        val result = quizCreateService.createQuiz(givenCommand)

        assertThat(result).isNotNull()
    }
}
