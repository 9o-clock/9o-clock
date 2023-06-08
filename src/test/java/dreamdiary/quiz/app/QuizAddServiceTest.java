package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuizAddServiceTest extends QuizTestSetUp {
    @InjectMocks
    private QuizAddService quizAddService;

    @DisplayName("퀴즈 생성 요청문을 퀴즈 생성 담당한테 전달")
    @Test
    void addQuiz_passes_request_generator() {
        final QuizAddRequest givenRequest = QuizAddRequest.builder().build();

        quizAddService.addQuiz(givenRequest);

        verify(mockQuizGenerator, times(1)).toQuiz(quizAddRequestCaptor.capture());
        assertNotNull(quizAddRequestCaptor.getValue());
        assertEquals(quizAddRequestCaptor.getValue(), givenRequest);
    }

    @DisplayName("동일한 제목이 존재하면 예외처리")
    @Test
    void addQuiz_throw_alreadyExistsTitle_exception() {
        final QuizAddRequest givenRequest = QuizAddRequest.builder().build();
        BDDMockito.given(mockQuizRepository.isTitleAlreadyExists(any())).willReturn(true);

        final QuizException exception = Assertions.assertThrows(QuizException.class, () ->
                quizAddService.addQuiz(givenRequest));

        assertEquals(exception.getMessage(), QuizException.duplicatedTitleExists().getMessage());
    }
}
