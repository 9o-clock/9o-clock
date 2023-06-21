package dreamdiary.quiz.domain.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Quiz {
    private final QuizPublicId quizPublicId;
    private final QuizTitle title;
    private final QuizContent content;
    private final Choices choices;
    private final LocalDateTime releaseAt;
    private final LocalDateTime answerReleaseAt;

    @Builder
    public Quiz(final QuizPublicId quizPublicId, final QuizTitle title, final QuizContent content, final Choices choices, final LocalDateTime releaseAt, final LocalDateTime answerReleaseAt) {
        if (null == quizPublicId || null == title || null == content) throw QuizException.invalidFormat();
        if (null == choices || null == releaseAt || null == answerReleaseAt) throw QuizException.invalidFormat();
        if (!answerReleaseAt.isAfter(releaseAt)) throw QuizException.invalidFormat();
        this.quizPublicId = quizPublicId;
        this.title = title;
        this.content = content;
        this.choices = choices;
        this.releaseAt = releaseAt;
        this.answerReleaseAt = answerReleaseAt;
    }

    public QuizSubmit submit(final String memberId, final String choicePublicId) {
        if (!StringUtils.hasText(choicePublicId)) throw QuizException.invalidFormat();
        if (this.choices.values().stream().filter(choice -> Objects.equals(choice.publicId(), choicePublicId)).findFirst().isEmpty()) throw QuizException.notFoundChoice();
        if (this.releaseAt.isAfter(LocalDateTime.now())) throw QuizException.notSubmitAt();
        return new QuizSubmit(this.quizPublicId, memberId, choicePublicId);
    }
}
