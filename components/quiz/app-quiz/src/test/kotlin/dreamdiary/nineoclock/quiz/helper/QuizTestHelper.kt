package dreamdiary.nineoclock.quiz.helper

import dreamdiary.nineoclock.quiz.app.usecase.ChoiceInfoDto
import dreamdiary.nineoclock.quiz.app.usecase.QuizAnswerSubmitCommand
import dreamdiary.nineoclock.quiz.app.usecase.QuizCreateCommand
import dreamdiary.nineoclock.quiz.domain.Choice
import dreamdiary.nineoclock.quiz.domain.ChoiceGroup
import dreamdiary.nineoclock.quiz.domain.Quiz
import dreamdiary.nineoclock.quiz.domain.QuizActionAuthorizer
import dreamdiary.nineoclock.quiz.domain.QuizAnswerSubmit
import dreamdiary.nineoclock.quiz.domain.QuizAnswerSubmitRepository
import dreamdiary.nineoclock.quiz.domain.QuizAuthorizeQuizSubmitActionResult
import dreamdiary.nineoclock.quiz.domain.QuizContent
import dreamdiary.nineoclock.quiz.domain.QuizRepository
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
    internal lateinit var mockQuizRepository: QuizRepository

    @Mock
    internal lateinit var mockQuizAnswerSubmitRepository: QuizAnswerSubmitRepository

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

    private val calculationQuizAnswerChoice: Choice = Choice("11", true)
    private val calculationChoice1: Choice = Choice("12", false)
    private val calculationChoice2: Choice = Choice("1", false)
    private val calculationChoice3: Choice = Choice("111", false)

    private val calculationQuiz: Quiz = Quiz(
        title = QuizTitle(" Calculation"),
        content = QuizContent("1 + 10?"),
        choiceGroup = ChoiceGroup(
            values = mutableListOf(
                calculationQuizAnswerChoice,
                calculationChoice1,
                calculationChoice2,
                calculationChoice3
            )
        ),
        releaseAt = LocalDateTime.now().minusDays(2),
        answerReleaseAt = LocalDateTime.now().minusDays(1)
    )

    @BeforeEach
    fun setUp() {
        setUpToSuccess()
    }

    internal fun setUpToSuccess() {
        Mockito.lenient().`when`(mockQuizRepository.findByPublicId(any())).thenReturn(anQuiz())
        Mockito.lenient().`when`(mockQuizActionAuthorizer.authorizeQuizCreateAction(any())).thenReturn(true)
        Mockito.lenient().`when`(mockQuizActionAuthorizer.authorizeQuizSubmitAction(any())).thenReturn(QuizAuthorizeQuizSubmitActionResult(true, "success"))
    }

    internal fun setUpToFailed() {

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
        return calculationQuiz
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

