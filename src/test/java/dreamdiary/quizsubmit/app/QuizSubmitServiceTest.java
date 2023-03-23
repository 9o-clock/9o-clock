package dreamdiary.quizsubmit.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("퀴즈 제출 서비스")
class QuizSubmitServiceTest {
    @InjectMocks
    private QuizSubmitService quizSubmitService;

    @DisplayName("제출한 퀴즈 정답이 알맞은 형식인지 ")
    @Test
    void submitQuiz_validate() {

    }
}
