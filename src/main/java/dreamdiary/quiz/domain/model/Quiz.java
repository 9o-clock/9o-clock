package dreamdiary.quiz.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Quiz {
    private final QuizPublicId quizPublicId;
    private final QuizTitle title;
    private final QuizContent content;
    private final Choices choices;
    private final LocalDateTime releaseAt;

    @Builder
    public Quiz(final QuizPublicId quizPublicId, final QuizTitle title, final QuizContent content, final Choices choices, final LocalDateTime releaseAt) {
        if (null == quizPublicId || null == title || null == content) throw QuizException.invalidFormat();
        if (null == choices || null == releaseAt) throw QuizException.invalidFormat();
        this.quizPublicId = quizPublicId;
        this.title = title;
        this.content = content;
        this.choices = choices;
        this.releaseAt = releaseAt;
    }
}
