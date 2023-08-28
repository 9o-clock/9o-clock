package dreamdiary.nineoclock.domain.quiz.model.exception;

import java.io.Serial;

public class ChoiceNotFoundException extends QuizException {
    @Serial
    private static final long serialVersionUID = 5114764404591552064L;

    public ChoiceNotFoundException() {
        super(CHOICE_NOT_FOUND);
    }
}
