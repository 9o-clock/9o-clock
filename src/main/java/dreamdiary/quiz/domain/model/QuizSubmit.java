package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

/**
 * 퀴즈 정답 제출은 Event Sourcing 패턴을 기반으로 할 예정이므로, 어떤 유저가 어느 퀴즈에 어떤 정답을 제출했는지가 중요.
 * @param quizPublicId
 * @param memberId
 * @param choicePublicId
 */
public record QuizSubmit(
        QuizPublicId quizPublicId,
        String memberId,
        String choicePublicId
) {
    public QuizSubmit {
        if (null == quizPublicId) throw QuizException.invalidFormat();
        if (!StringUtils.hasText(memberId)) throw QuizException.invalidFormat();
        if (!StringUtils.hasText(choicePublicId)) throw QuizException.invalidFormat();
    }
}
