package dreamdiary.quiz.domain.model;

import lombok.Getter;

@Getter
public class QuizException extends RuntimeException {
    private static final long serialVersionUID = 6384709605145348794L;

    private QuizException(final String message) {
        super(message);
    }

    public static QuizException invalidFormat() {
        return new QuizException("퀴즈 형식이 잘못되었습니다.");
    }

    public static QuizException duplicatedTitleExists() {
        return new QuizException("동일한 퀴즈 제목이 존재합니다.");
    }

    public static QuizException notFoundQuiz() {
        return new QuizException("요청한 퀴즈를 찾을 수 없습니다.");
    }
}
