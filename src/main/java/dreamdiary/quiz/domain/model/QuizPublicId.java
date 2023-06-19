package dreamdiary.quiz.domain.model;

public record QuizPublicId(
        String value
) {
    public QuizPublicId {
        if (null == value) throw QuizException.invalidFormat();
    }
}
