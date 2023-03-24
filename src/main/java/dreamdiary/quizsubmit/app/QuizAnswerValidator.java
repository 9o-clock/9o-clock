package dreamdiary.quizsubmit.app;

import dreamdiary.support.advice.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class QuizAnswerValidator {
    public void validate(final Integer answer) {
        if (1 > answer || 4 < answer) throw RequestException.of(HttpStatus.BAD_REQUEST, "퀴즈 정답 양식이 잘못되었습니다.");
    }
}
