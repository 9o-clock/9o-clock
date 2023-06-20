package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.helper.QuizTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;

class QuizSubmitServiceTest extends QuizTestHelper {
    @InjectMocks
    private QuizSubmitService quizSubmitService;

    @DisplayName("퀴즈가 존재하지 않을 경우 예외처리")
    @Test
    void submitQuiz_throw_not_found_quiz_exception() {
        final String givenPublicId = "quizPublicId";
        final QuizSubmitRequest givenRequest = anQuizSubmitRequest().build();
        BDDMockito.given(mockQuizRepository.findBy((QuizPublicId) any())).willReturn(Optional.empty());

        final QuizException exception = catchThrowableOfType(() ->
                        quizSubmitService.submitQuiz(givenPublicId, givenRequest),
                QuizException.class);

        assertThat(exception.getMessage()).isEqualTo(QuizException.notFoundQuiz().getMessage());
    }
}
