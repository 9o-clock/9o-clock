package dreamdiary.quiz.domain.model.exception;

import java.io.Serial;

public class DuplicatedTitleExistsException extends QuizException {
    @Serial
    private static final long serialVersionUID = 8784409813864220004L;

    public DuplicatedTitleExistsException() {
        super(DUPLICATED_TITLE_EXISTS);
    }
}
