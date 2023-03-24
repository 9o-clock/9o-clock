package dreamdiary.quizsubmit.app;

import dreamdiary.support.advice.exception.RequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("퀴즈 제출 양식 Validator")
class QuizAnswerValidatorTest {
    @InjectMocks
    private QuizAnswerValidator quizAnswerValidator;

    @DisplayName("퀴즈 정답이 1~4 이외일 경우 예외처리합니다.")
    @Test
    void validate_throwException() {
        final RequestException exception = Assertions.assertThrows(RequestException.class,
                () -> quizAnswerValidator.validate(5));

        assertThat(exception).isNotNull();
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getMessage()).isEqualTo("퀴즈 정답 양식이 잘못되었습니다.");
    }
}