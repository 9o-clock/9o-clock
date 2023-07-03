package dreamdiary.quiz.domain.model.exception;

import java.io.Serial;

public class InvalidQuizFormatException extends QuizException {
    @Serial
    private static final long serialVersionUID = -7051235983357662345L;

    public InvalidQuizFormatException() {
        super(INVALID_FORMAT);
    }
}
