package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizSubmit(
        QuizPublicId quizPublicId,
        String memberId,
        String choiceId
) {
    public QuizSubmit {
        if (null == quizPublicId) throw QuizException.invalidFormat();
        if (StringUtils.hasText(memberId)) throw QuizException.invalidFormat();
        if (StringUtils.hasText(choiceId)) throw QuizException.invalidFormat();
    }
}
