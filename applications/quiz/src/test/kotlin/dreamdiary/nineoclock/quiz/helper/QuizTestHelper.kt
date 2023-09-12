package dreamdiary.nineoclock.quiz.helper

import dreamdiary.nineoclock.quiz.application.port.inbound.ChoiceInfoDto
import dreamdiary.nineoclock.quiz.application.port.inbound.QuizAnswerSubmitCommand
import dreamdiary.nineoclock.quiz.application.port.inbound.QuizCreateCommand
import dreamdiary.nineoclock.quiz.application.port.outbound.QuizAnswerSubmitOutPort
import dreamdiary.nineoclock.quiz.application.port.outbound.QuizOutPort
import dreamdiary.nineoclock.quiz.domain.Choice
import dreamdiary.nineoclock.quiz.domain.ChoiceGroup
import dreamdiary.nineoclock.quiz.domain.Quiz
import dreamdiary.nineoclock.quiz.domain.QuizActionAuthorizer
import dreamdiary.nineoclock.quiz.domain.QuizAnswerSubmit
import dreamdiary.nineoclock.quiz.domain.QuizContent
import dreamdiary.nineoclock.quiz.domain.QuizTitle
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
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

    @Mock
    internal lateinit var mockQuizAnswerSubmitOutPort: QuizAnswerSubmitOutPort

    @Captor
    internal lateinit var quizCreateCommandCaptor: ArgumentCaptor<QuizCreateCommand>

    @Captor
    internal lateinit var quizAnswerSubmitCommandCaptor: ArgumentCaptor<QuizAnswerSubmitCommand>

    @Captor
    internal lateinit var creatorIdCaptor: ArgumentCaptor<String>

    @Captor
    internal lateinit var quizCaptor: ArgumentCaptor<Quiz>

    @Captor
    internal lateinit var quizPublicIdCaptor: ArgumentCaptor<QuizPublicId>

    @Captor
    internal lateinit var quizAnswerSubmitCaptor: ArgumentCaptor<QuizAnswerSubmit>

    @BeforeEach
    fun setUp() {
        Mockito.lenient().`when`(mockQuizOutPort.findByPublicId(any())).thenReturn(anQuiz())
        Mockito.lenient().`when`(mockQuizActionAuthorizer.authorizeQuizCreateAction(any())).thenReturn(true)
        Mockito.lenient().`when`(mockQuizActionAuthorizer.authorizeQuizSubmitAction(any())).thenReturn(true)
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

    internal fun anQuizAnswerSubmitCommand(): QuizAnswerSubmitCommand {
        return QuizAnswerSubmitCommand(
            submitterSecureId = "givenSubmitterSecureId",
            quizPublicId = "givenQuizPublicId",
            choicePublicId = "givenChoicePublicId"
        )
    }

    internal fun anQuiz(): Quiz {
        return Quiz(
            title = QuizTitle("QuizTitle"),
            content = QuizContent("QuizContent"),
            choiceGroup = ChoiceGroup(
                values = mutableListOf(
                    anChoice(),
                    anChoice()
                )
            ),
            releaseAt = LocalDateTime.now().minusDays(2),
            answerReleaseAt = LocalDateTime.now().minusDays(1)
        )
    }

    internal fun customQuiz(choices: Collection<Choice>): Quiz {
        return Quiz(
            title = QuizTitle("QuizTitle"),
            content = QuizContent("QuizContent"),
            choiceGroup = ChoiceGroup(
                values = choices
            ),
            releaseAt = LocalDateTime.now(),
            answerReleaseAt = LocalDateTime.now()
        )
    }

    internal fun anChoice(): Choice {
        return Choice(
            value = "A",
            isAnswer = false
        )
    }

}

internal fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

internal fun <T> any(): T {
    Mockito.any<T>()
    return null as T
}

