package dreamdiary.noc.quiz.application

import dreamdiary.noc.quiz.app.ChoiceInfoDto
import dreamdiary.noc.quiz.app.QuizCreateCommand
import dreamdiary.noc.quiz.app.QuizCreateService
import dreamdiary.noc.quiz.domain.model.Quiz
import dreamdiary.noc.quiz.domain.model.QuizRepository
import dreamdiary.noc.shard.exception.ValidationException
import jakarta.validation.Validation
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.capture
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class QuizCreateServiceTest {
    @Captor
    private lateinit var quizCaptor: ArgumentCaptor<Quiz>
    @Mock
    private lateinit var quizRepository: QuizRepository
    @Spy
    private val validator = Validation.buildDefaultValidatorFactory().validator
    @InjectMocks
    private lateinit var quizCreateService: QuizCreateService

    @DisplayName("입력받은 정보를 검증 및 예외처리 합니다.")
    @Test
    fun createQuiz_validate_command_and_throw() {
        val givenCommand = QuizCreateCommand(
            creatorId = "",
            title = "",
            content = "",
            choices = listOf(),
            releaseAt = LocalDateTime.now(),
            answerReleaseAt = LocalDateTime.now()
        )

        assertThatCode {
            quizCreateService.createQuiz(givenCommand)
        }.isInstanceOf(ValidationException::class.java)

        verify(validator, times(1)).validate(givenCommand)
    }

    @DisplayName("퀴즈를 저장합니다.")
    @Test
    fun createQuiz_save_quiz() {
        val givenCommand = anQuizCreateCommand()

        quizCreateService.createQuiz(givenCommand)

        verify(quizRepository, times(1)).save(capture(quizCaptor))
    }

    @DisplayName("퀴즈 생성 요청 결과를 반환합니다.")
    @Test
    fun createQuiz_return_value() {
        val givenCommand = anQuizCreateCommand()

        val result = quizCreateService.createQuiz(givenCommand)

        verify(quizRepository, times(1)).save(capture(quizCaptor))
        assertThat(result.quizPublicId).isEqualTo(quizCaptor.value.id.publicId.value)
    }

    private fun anQuizCreateCommand(): QuizCreateCommand {
        return QuizCreateCommand(
            creatorId = "givenCreator",
            title = "givenTitle",
            content = "givenContent",
            choices = listOf(ChoiceInfoDto("A", true), ChoiceInfoDto("B", false), ChoiceInfoDto("C", false)),
            releaseAt = LocalDateTime.now(),
            answerReleaseAt = LocalDateTime.now()
        )
    }
}
