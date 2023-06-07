package dreamdiary.quiz.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Quiz {
    private final QuizWriter writer;
    private final QuizTitle title;
    private final QuizContent content;
    private final Choices choices;
    private final LocalDateTime releaseAt;

    @Builder
    public Quiz(final QuizWriter writer, final QuizTitle title, final QuizContent content, final Choices choices, final LocalDateTime releaseAt) {
        this.choices = choices;
        if (null == writer || null == title || null == content || null == choices || null == releaseAt) throw QuizException.invalidFormat();
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.releaseAt = releaseAt;
    }
}
