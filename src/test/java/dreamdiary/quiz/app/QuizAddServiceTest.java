package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.model.QuizException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuizAddServiceTest extends QuizTestHelper {
    @InjectMocks
    private QuizAddService quizAddService;

    @DisplayName("퀴즈 생성 요청문을 퀴즈 생성 담당한테 전달")
    @Test
    void addQuiz_passes_request_generator() {
        final QuizAddRequest givenRequest = QuizAddRequest.builder().build();

        quizAddService.addQuiz(givenRequest);

        verify(mockQuizGenerator, times(1)).generateQuiz(quizAddRequestCaptor.capture());
        assertThat(quizAddRequestCaptor.getValue()).isNotNull();
    }

    @DisplayName("동일한 제목이 존재하면 예외처리")
    @Test
    void addQuiz_throw_alreadyExistsTitle_exception() {
        final QuizAddRequest givenRequest = QuizAddRequest.builder().build();
        BDDMockito.given(mockQuizRepository.isTitleAlreadyExists(any())).willReturn(true);

        final QuizException exception = Assertions.assertThrows(QuizException.class, () ->
                quizAddService.addQuiz(givenRequest));
        assertThat(exception.getMessage()).isEqualTo(QuizException.duplicatedTitleExists().getMessage());
    }

    @DisplayName("퀴즈 생성 성공 이벤트 발행")
    @Test
    void addQuiz_publish_quiz_generated_event() {
        final QuizAddRequest givenRequest = QuizAddRequest.builder().build();

        quizAddService.addQuiz(givenRequest);

        verify(mockPublisher, times(1)).publishEvent(publishEventCaptor.capture());
    }
}
