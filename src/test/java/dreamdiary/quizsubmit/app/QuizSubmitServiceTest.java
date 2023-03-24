package dreamdiary.quizsubmit.app;

import dreamdiary.quizsubmit.domain.QuizFindService;
import dreamdiary.support.advice.exception.RequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("퀴즈 제출 서비스")
class QuizSubmitServiceTest {
    @InjectMocks
    private QuizSubmitService quizSubmitService;
    @Mock
    private QuizAnswerValidator mockQuizAnswerValidator;
    @Mock
    private QuizFindService mockQuizFindService;
    @Captor
    private ArgumentCaptor<Integer> answerCaptor;
    @Captor
    private ArgumentCaptor<Long> quizIdCaptor;

    @DisplayName("제출한 퀴즈가 알맞은 양식인지 검사하기 위해 validator에게 요청합니다.")
    @Test
    void submitQuiz_passesAnswerToValidator() {
        final Integer givenAnswer = 1;
        quizSubmitService.submitQuiz(1L, 1L, givenAnswer);

        Mockito.verify(mockQuizAnswerValidator, times(1)).validate(answerCaptor.capture());
        Assertions.assertThat(answerCaptor.getValue()).isNotNull();
        Assertions.assertThat(answerCaptor.getValue()).isEqualTo(givenAnswer);
    }

    @DisplayName("퀴즈가 존재하는지 확인하기 위해 서비스에 요청합니다.")
    @Test
    void submitQuiz_passesQuizIdToFindQuizService() {
        final Long givenQuizId = 1L;
        quizSubmitService.submitQuiz(1L, givenQuizId, 1);

        Mockito.verify(mockQuizFindService, times(1)).findQuiz(quizIdCaptor.capture());
        Assertions.assertThat(quizIdCaptor.getValue()).isNotNull();
        Assertions.assertThat(quizIdCaptor.getValue()).isEqualTo(givenQuizId);
    }

    @DisplayName("서비스에 요청하여 받은 퀴즈 정보가 존재하지 않을 경우 예외처리합니다.")
    @Test
    void submitQuiz_FindQuizResponseIsEmptyToThrowException() {
        BDDMockito.given(mockQuizFindService.findQuiz(any())).willReturn(Optional.empty());


        RequestException exception = assertThrows(RequestException.class,
                () -> quizSubmitService.submitQuiz(1L, 1L, 1));

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(exception.getMessage()).isEqualTo("요청한 퀴즈 정보가 존재하지 않습니다.");
    }
}
