package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizSubmit(
        QuizPublicId quizPublicId,
        String memberId,
        String choicePublicId
) {
    public QuizSubmit {
        if (null == quizPublicId) throw QuizException.invalidFormat();
        if (StringUtils.hasText(memberId)) throw QuizException.invalidFormat();
        if (StringUtils.hasText(choicePublicId)) throw QuizException.invalidFormat();
    }
}
