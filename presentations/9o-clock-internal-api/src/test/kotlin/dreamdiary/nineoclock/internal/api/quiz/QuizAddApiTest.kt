package dreamdiary.nineoclock.internal.api.quiz

import dreamdiary.nineoclock.application.quiz.usecase.QuizCreateCommand
import dreamdiary.nineoclock.application.quiz.usecase.QuizCreateUseCase
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [QuizAddApi::class])
internal class QuizAddApiTest {
    @Autowired
    lateinit var webClient:WebTestClient
    @MockBean
    lateinit var mockQuizCreateUseCase: QuizCreateUseCase
    @Captor
    lateinit var commandCaptor: ArgumentCaptor<QuizCreateCommand>

    @Test
    fun addQuiz_status_is_created() {
        val givenRequest = anAddRequest()
        webClient.post()
            .uri("/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(givenRequest))
            .exchange()
            .expectStatus().isCreated
    }

    @Test
    fun addQuiz_passes_data_to_useCase() {
        val givenRequest = anAddRequest()
        webClient.post()
            .uri("/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(givenRequest))
            .exchange()

        Mockito.verify(mockQuizCreateUseCase, Mockito.times(1)).createQuiz(capture(commandCaptor))
        assertThat(commandCaptor.value).isNotNull
        assertThat(commandCaptor.value.title).isEqualTo(givenRequest.title)
        assertThat(commandCaptor.value.content).isEqualTo(givenRequest.content)
        assertThat(commandCaptor.value.choices).isEqualTo(givenRequest.choices)
        assertThat(commandCaptor.value.releaseAt.truncatedTo(ChronoUnit.SECONDS)).isEqualTo(givenRequest.releaseAt.truncatedTo(ChronoUnit.SECONDS))
        assertThat(commandCaptor.value.answerReleaseAt.truncatedTo(ChronoUnit.SECONDS)).isEqualTo(givenRequest.answerReleaseAt.truncatedTo(ChronoUnit.SECONDS))
    }

    fun anAddRequest() :QuizAddRequest {
        return QuizAddRequest(
            title = "givenTitle",
            content = "givenContent",
            choices = listOf("A", "B", "C"),
            releaseAt = LocalDateTime.now(),
            answerReleaseAt = LocalDateTime.now()
        )
    }
}
