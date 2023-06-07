package dreamdiary.quiz.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Quiz {
    private final QuizWriter writer;
    private final QuizTitle title;
    private final QuizContent content;
    private final LocalDateTime releaseAt;

    public Quiz(final QuizWriter writer, final QuizTitle title, final QuizContent content, final LocalDateTime releaseAt) {
        if (null == writer || null == title || null == content || null == releaseAt) throw QuizException.InvalidFormat();
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.releaseAt = releaseAt;
    }
}
