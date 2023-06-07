package dreamdiary.quiz.domain;

public class QuizException extends RuntimeException {
    private QuizException(final String message) {
        super(message);
    }

    public static QuizException InvalidFormat() {
        return new QuizException("퀴즈 형식이 잘못되었습니다.");
    }
}
