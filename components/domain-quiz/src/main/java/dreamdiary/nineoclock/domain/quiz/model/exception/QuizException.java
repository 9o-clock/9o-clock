package dreamdiary.nineoclock.domain.quiz.model.exception;

public abstract class QuizException extends RuntimeException {
    private static final long serialVersionUID = 855301809476699168L;
    protected static final String INVALID_FORMAT = "퀴즈 형식이 잘못되었습니다.";
    protected static final String DUPLICATED_TITLE_EXISTS = "동일한 퀴즈 제목이 존재합니다.";
    protected static final String SUBMITTER_NOT_FOUND = "참여자의 정보를 찾을 수 없습니다.";
    protected static final String QUIZ_NOT_FOUND = "요청한 퀴즈를 찾을 수 없습니다.";
    protected static final String CHOICE_NOT_FOUND = "요청한 정답을 찾을 수 없습니다.";
    protected static final String NOT_SUBMIT_AT = "정답 제출이 가능한 일자가 아닙니다.";

    protected QuizException(final String message) {
        super(message);
    }

    protected QuizException(final String message, final Throwable cause) {
        super(message, cause);
    }

    protected QuizException(final Throwable cause) {
        super(cause);
    }

    protected QuizException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
