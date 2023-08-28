package dreamdiary.nineoclock.domain.quiz.model.exception;

import java.io.Serial;

public class SubmitterNotFoundException extends QuizException {
    @Serial
    private static final long serialVersionUID = -1355441804360800432L;

    public SubmitterNotFoundException() {
        super(SUBMITTER_NOT_FOUND);
    }
}
