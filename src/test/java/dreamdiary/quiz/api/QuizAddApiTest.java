package dreamdiary.quiz.api;

import dreamdiary.quiz.app.QuizAddRequest;
import dreamdiary.quiz.app.QuizAddUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
                        .content("{}"))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void addQuiz_passes_data_useCase() throws Exception {
        final String givenTitle = "Quiz Title";
        final String givenContent = "Quiz Content";
        final List<String> givenChoices = List.of("강아지", "고양이", "토끼", "람쥐");
        final LocalDateTime givenReleaseAt = LocalDateTime.now().plusDays(30L);

        mockMvc.perform(post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "%s",
                            "content": "%s",
                            "releaseAt": "%s",
                            "choices": %s
                        }
                        """.formatted(givenTitle, givenContent, givenReleaseAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREA)),
                        givenChoices.stream().map(choice -> "\"" + choice + "\"").toList())
                ));

        final ArgumentCaptor<QuizAddRequest> captor = ArgumentCaptor.forClass(QuizAddRequest.class);

        verify(quizAddUseCase, times(1)).addQuiz(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals(givenTitle, captor.getValue().getTitle());
        assertEquals(givenContent, captor.getValue().getContent());
        assertArrayEquals(givenChoices.toArray(), captor.getValue().getChoices().toArray());
    }
}
