package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

public record QuizSubmit(
        QuizPublicId quizPublicId,
        SubmitterUniqId submitterUniqId,
        String choicePublicId
) {
    public QuizSubmit {
        if (null == quizPublicId) throw QuizException.invalidFormat();
        if (null == submitterUniqId) throw QuizException.invalidFormat();
        if (!StringUtils.hasText(choicePublicId)) throw QuizException.invalidFormat();
    }
}
