package dreamdiary.quiz.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class QuizSubmitApiTest {
    @InjectMocks
    private QuizSubmitApi quizSubmitApi;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizSubmitApi).build();
    }
//
//    @Test
//    void submitQuiz_status_is_ok() throws Exception {
//        /** TODO 퀴즈의 아이디가 정수형이면 악의적인 사용자가 임의로 요청할 수 있음.
//         * 시스템 레벨에서 체크해서 막으면 되지만 애당초 공격 대상이 된다는게 좋진않아보임.
//         * 해당 문제를 줄이기 위해 UUID 등의 문자열 기반 토큰을 아이디로 식별할 생각.
//         */
//        mockMvc.perform(post("/quizzes/{quizId}/submit", 1))
//
//        ;
//    }
}
