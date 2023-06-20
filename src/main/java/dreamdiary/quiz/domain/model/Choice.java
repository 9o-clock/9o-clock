package dreamdiary.quiz.domain.model;

import org.springframework.util.StringUtils;

import java.util.UUID;

public record Choice(
        String id,
        String value,
        Boolean isAnswer
) {

    public Choice(final String id, final String value) {
        this(id, value, true);
    }

    public Choice(final String value) {
        this(UUID.randomUUID().toString(), value, true);
    }

    public Choice {
        if (!StringUtils.hasText(id)) throw QuizException.invalidFormat();
        if (!StringUtils.hasText(value) || 10 < value.length()) throw QuizException.invalidFormat();
        if (null == isAnswer) throw QuizException.invalidFormat();
    }
}
