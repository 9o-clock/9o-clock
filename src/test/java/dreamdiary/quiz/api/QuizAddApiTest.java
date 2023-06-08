package dreamdiary.quiz.api;

import dreamdiary.quiz.app.QuizAddUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class QuizAddApiTest {
    @InjectMocks
    private QuizAddApi quizAddApi;
    @Mock
    private QuizAddUseCase quizAddUseCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizAddApi).build();
    }

    @Test
    void addQuiz_status_is_created() throws Exception {
        mockMvc.perform(post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void addQuiz_passes_data_useCase() throws Exception {
        mockMvc.perform(post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""));
        verify(quizAddUseCase, times(1)).addQuiz(null);
    }
}
