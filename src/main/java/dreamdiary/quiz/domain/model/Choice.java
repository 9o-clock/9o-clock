package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public record Choice(
        String publicId,
        String value,
        Boolean isAnswer
) {

    public Choice(final String id, final String value) {
        this(id, value, true);
    }

    public Choice(final String value) {
        this(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)) , value, true);
    }

    public Choice {
        if (!StringUtils.hasText(publicId)) throw new InvalidQuizFormatException();
        if (!StringUtils.hasText(value) || 10 < value.length()) throw new InvalidQuizFormatException();
        if (null == isAnswer) throw new InvalidQuizFormatException();
    }
}
