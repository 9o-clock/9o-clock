package dreamdiary.nineoclock.quiz.helper

import dreamdiary.nineoclock.quiz.application.usecase.ChoiceInfoDto
import dreamdiary.nineoclock.quiz.application.usecase.QuizCreateCommand
import dreamdiary.nineoclock.quiz.domain.model.Quiz
import dreamdiary.nineoclock.quiz.domain.outbound.QuizOutPort
import dreamdiary.nineoclock.quiz.domain.service.QuizActionAuthorizer
import jakarta.validation.Validator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
open class QuizTestHelper {
    @Mock
    internal lateinit var mockValidator: Validator

    @Mock
    internal lateinit var mockQuizActionAuthorizer: QuizActionAuthorizer

    @Mock
    internal lateinit var mockQuizOutPort: QuizOutPort

    @Captor
    internal lateinit var quizCreateCommandCaptor: ArgumentCaptor<QuizCreateCommand>

    @Captor
    internal lateinit var creatorIdCaptor: ArgumentCaptor<String>

    @Captor
    internal lateinit var quizCaptor: ArgumentCaptor<Quiz>

    @BeforeEach
    fun setUp() {
        Mockito.lenient().`when`(mockQuizActionAuthorizer.authorizeQuizCreateAction(any())).thenReturn(true)
    }

    internal fun anQuizCreateCommand(): QuizCreateCommand {
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

internal fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

internal fun <T> any(): T {
    Mockito.any<T>()
    return null as T
}

