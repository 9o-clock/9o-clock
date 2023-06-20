package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizSubmit(
        QuizPublicId quizId,
        String memberId,
        String choiceId
) {
    public QuizSubmit {
        if (null == quizId) throw QuizException.invalidFormat();
        if (StringUtils.hasText(memberId)) throw QuizException.invalidFormat();
        if (StringUtils.hasText(choiceId)) throw QuizException.invalidFormat();
    }
}
