package dreamdiary.quizsubmit.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("퀴즈 제출 서비스")
class QuizSubmitServiceTest {
    @InjectMocks
    private QuizSubmitService quizSubmitService;
    @Mock
    private QuizAnswerValidator mockQuizAnswerValidator;
    @Captor
    private ArgumentCaptor<Integer> answerCaptor;
    @DisplayName("제출한 퀴즈가 알맞은 양식인지 검사하기 위해 validator에게 요청합니다.")
    @Test
    void submitQuiz_passesAnswerToValidator() {
        final Integer givenAnswer = 1;
        quizSubmitService.submitQuiz(1L, 1L, givenAnswer);

        Mockito.verify(mockQuizAnswerValidator, Mockito.times(1)).validate(answerCaptor.capture());
        Assertions.assertThat(answerCaptor.getValue()).isNotNull();
        Assertions.assertThat(answerCaptor.getValue()).isEqualTo(givenAnswer);
    }
}
