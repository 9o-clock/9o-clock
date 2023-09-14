package dreamdiary.nineoclock.quiz.command

import dreamdiary.nineoclock.quiz.application.port.inbound.QuizCreateUseCase
import org.assertj.core.api.Assertions.*
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class QuizCreateApiTest {
    lateinit var mockMvc: MockMvc
    @InjectMocks
    lateinit var quizCreateApi: QuizCreateApi
    @Mock
    lateinit var mockQuizCreateUseCase: QuizCreateUseCase

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizCreateApi).build()
        lenient().`when`(mockQuizCreateUseCase.createQuiz(any())).thenReturn("TTTT")
    }

    @DisplayName("퀴즈 생성 요청 결과는 status=CREATED(201)입니다.")
    @Test
    fun createQuiz_status_is_created() {
        mockMvc.perform(post("/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                {
                  "title": "givenTitle",
                  "content": "content",
                  "choices": [
                      {
                        "value": "givenChoice",
                        "isAnswer": true
                      }
                  ],
                  "releaseAt": "2000-01-01 10:00:00",
                  "answerReleaseAt": "2000-12-31 14:00:00"
                }
                """.trimIndent()))
            .andExpect(status().isCreated)
    }

    @DisplayName("퀴즈 생성 요청 정보를 전달합니다.")
    @Test
    fun createQuiz_passes_request_to_useCase() {
        mockMvc.perform(post("/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                {
                  "title": "givenTitle",
                  "content": "content",
                  "choices": [
                      {
                        "value": "givenChoice",
                        "isAnswer": true
                      }
                  ],
                  "releaseAt": "2000-01-01 10:00:00",
                  "answerReleaseAt": "2000-12-31 14:00:00"
                }
                """.trimIndent()))

        verify(mockQuizCreateUseCase, times(1)).createQuiz(any())
    }

    @DisplayName("퀴즈 생성 요청 결과를 반환합니다.")
    @Test
    fun createQuiz_return_value() {
        val givenResult = "givenResult"
        BDDMockito.given(mockQuizCreateUseCase.createQuiz(any())).willReturn(givenResult)

        mockMvc.perform(post("/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                {
                  "title": "givenTitle",
                  "content": "content",
                  "choices": [
                      {
                        "value": "givenChoice",
                        "isAnswer": true
                      }
                  ],
                  "releaseAt": "2000-01-01 10:00:00",
                  "answerReleaseAt": "2000-12-31 14:00:00"
                }
                """.trimIndent()))
            .andExpect(jsonPath("$.id", equalTo(givenResult)))
    }
}

internal fun <T> any(): T {
    Mockito.any<T>()
    return null as T
}


