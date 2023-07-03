package dreamdiary.quiz.domain.model.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class QuizNotFoundException extends QuizException {
    @Serial
    private static final long serialVersionUID = -8280885483216443801L;

    public QuizNotFoundException() {
        super(QUIZ_NOT_FOUND);
    }
}
