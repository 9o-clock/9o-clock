package dreamdiary.nineoclock.domain.quiz.model.exception;

import java.io.Serial;

public class NotSubmitAtException extends QuizException {
    @Serial
    private static final long serialVersionUID = -5396926194040562036L;

    public NotSubmitAtException() {
        super(NOT_SUBMIT_AT);
    }
}
