package dreamdiary.quizsubmit.app;

import dreamdiary.quizsubmit.domain.QuizFindResponse;
import dreamdiary.quizsubmit.domain.QuizFindService;
import dreamdiary.quizsubmit.domain.QuizSubmit;
import dreamdiary.quizsubmit.domain.QuizSubmitRepository;
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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    private QuizSubmitRepository mockQuizSubmitRepository;

    @Captor
    private ArgumentCaptor<Long> userIdCaptor;
    @Captor
    private ArgumentCaptor<Integer> answerCaptor;
    @Captor
    private ArgumentCaptor<Long> quizIdCaptor;

    @Captor
    private ArgumentCaptor<QuizSubmit> quizSubmitCaptor;

    @DisplayName("제출한 퀴즈가 알맞은 양식인지 검사하기 위해 validator에게 요청합니다.")
    @Test
    void submitQuiz_passesAnswerToValidator() {
        final Integer givenAnswer = 1;
        try {
            quizSubmitService.submitQuiz(1L, 1L, givenAnswer);
        } catch (Throwable ignore) {}

        Mockito.verify(mockQuizAnswerValidator, times(1)).validate(answerCaptor.capture());
        Assertions.assertThat(answerCaptor.getValue()).isNotNull();
        Assertions.assertThat(answerCaptor.getValue()).isEqualTo(givenAnswer);
    }

    @DisplayName("퀴즈가 존재하는지 확인하기 위해 서비스에 요청합니다.")
    @Test
    void submitQuiz_passesQuizIdToFindQuizService() {
        final Long givenQuizId = 1L;

        try {
            quizSubmitService.submitQuiz(1L, givenQuizId, 1);
        } catch (Throwable ignore) {}

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

    @DisplayName("퀴즈의 공개일이 현재 일자가 아닐 경우 예외처리 합니다.")
    @Test
    void submitQuiz_Exception_Quiz_ReleaseDate_is_not_CurrentDate() {
        final QuizFindResponse givenResponse = new QuizFindResponse(1L, "title", LocalDate.now().minusDays(1));
        BDDMockito.given(mockQuizFindService.findQuiz(any())).willReturn(Optional.of(givenResponse));

        RequestException exception = assertThrows(RequestException.class,
                () -> quizSubmitService.submitQuiz(1L, 1L, 1));

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(exception.getMessage()).isEqualTo("해당 퀴즈는 정답을 제출할 수 없습니다.");
    }

    @DisplayName("퀴즈 정답 중복 제출은 예외처리 합니다.")
    @Test
    void submitQuiz_Exception_DuplicateAnswerSubmit() {
        final Long givenUserId = 1L;
        final Long givenQuizId = 2L;
        final QuizFindResponse givenResponse = new QuizFindResponse(givenQuizId, "title", LocalDate.now());
        BDDMockito.given(mockQuizFindService.findQuiz(any())).willReturn(Optional.of(givenResponse));
        BDDMockito.given(mockQuizSubmitRepository.existsQuizSubmitByUserIdAndQuizId(anyLong(), anyLong())).willReturn(true);

        RequestException exception = assertThrows(RequestException.class,
                () -> quizSubmitService.submitQuiz(givenUserId, givenQuizId, 1));

        Mockito.verify(mockQuizSubmitRepository,  times(1)).existsQuizSubmitByUserIdAndQuizId(userIdCaptor.capture(), quizIdCaptor.capture());
        Assertions.assertThat(userIdCaptor.getValue()).isNotNull();
        Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(givenUserId);
        Assertions.assertThat(quizIdCaptor.getValue()).isNotNull();
        Assertions.assertThat(quizIdCaptor.getValue()).isEqualTo(givenQuizId);
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(exception.getMessage()).isEqualTo("이미 해당 퀴즈의 정답을 제출하였습니다.");
    }

    @DisplayName("제출한 퀴즈 정답을 저장홥니다.")
    @Test
    void submitQuiz_QuizSubmitSaveToRepository() {
        final Long givenUserId = 1L;
        final Long givenQuizId = 2L;
        final Integer givenAnswer = 3;
        final QuizFindResponse givenResponse = new QuizFindResponse(givenQuizId, "title", LocalDate.now());
        BDDMockito.given(mockQuizFindService.findQuiz(any())).willReturn(Optional.of(givenResponse));
        BDDMockito.given(mockQuizSubmitRepository.existsQuizSubmitByUserIdAndQuizId(anyLong(), anyLong())).willReturn(false);

        quizSubmitService.submitQuiz(givenUserId, givenQuizId, givenAnswer);

        Mockito.verify(mockQuizSubmitRepository,  times(1)).save(quizSubmitCaptor.capture());
        Assertions.assertThat(quizSubmitCaptor.getValue()).isNotNull();
        Assertions.assertThat(quizSubmitCaptor.getValue().getUserId()).isEqualTo(givenUserId);
        Assertions.assertThat(quizSubmitCaptor.getValue().getQuizId()).isEqualTo(givenQuizId);
        Assertions.assertThat(quizSubmitCaptor.getValue().getAnswer()).isEqualTo(givenAnswer);
    }
}
