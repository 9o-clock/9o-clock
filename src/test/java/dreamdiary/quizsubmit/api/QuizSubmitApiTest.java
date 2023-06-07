package dreamdiary.quizsubmit.api;

import com.sun.security.auth.UserPrincipal;
import dreamdiary.quizsubmit.app.QuizSubmitService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("퀴즈 제출 Api")
class QuizSubmitApiTest {
    private MockMvc mockMvc;
    @InjectMocks
    private QuizSubmitApi quizSubmitApi;
    @Mock
    private QuizSubmitService mockQuizSubmitService;
    @Captor
    private ArgumentCaptor<Long> userIdCaptor;
    @Captor
    private ArgumentCaptor<Long> quizIdCaptor;
    @Captor
    private ArgumentCaptor<Integer> answerCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizSubmitApi).build();
    }

    @DisplayName("퀴즈 정답 제출 성공 status=200")
    @Test
    void submitQuiz_returnOkHttpStatus() throws Exception {
        mockMvc.perform(post("/quizzes/{quizId}/submissions", 1)
                        .principal(new UserPrincipal("1"))
                        .param("answer", "1"))
                .andExpect(status().isOk());
    }

    @DisplayName("퀴즈 정답 제출 요청 정보를 서비스에 전달합니다.")
    @Test
    void submitQuiz_passesDataToService() throws Exception {
        final Long givenUserId = 1L;
        final Long givenQuizId = 10L;
        final Integer givenAnswer = 4;

        mockMvc.perform(post("/quizzes/{quizId}/submissions", givenQuizId)
                .principal(new UserPrincipal(String.valueOf(givenUserId)))
                .param("answer", String.valueOf(givenAnswer)));

        Mockito.verify(mockQuizSubmitService, Mockito.times(1)).submitQuiz(userIdCaptor.capture(), quizIdCaptor.capture(), answerCaptor.capture());
        Assertions.assertThat(userIdCaptor.getValue()).isNotNull();
        Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(givenUserId);
        Assertions.assertThat(quizIdCaptor.getValue()).isNotNull();
        Assertions.assertThat(quizIdCaptor.getValue()).isEqualTo(givenQuizId);
        Assertions.assertThat(answerCaptor.getValue()).isNotNull();
        Assertions.assertThat(answerCaptor.getValue()).isEqualTo(givenAnswer);
    }

    @DisplayName("제출한 퀴즈의 정답 가져오기 성공 시 status=200")
    @Test
    void getSubmitQuiz() throws Exception {
        mockMvc.perform(get("/quizzes/{quizId}/submissions", 1)
                .principal(new UserPrincipal("1")))
                .andExpect(status().isOk());
    }
}
