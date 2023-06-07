package dreamdiary.quiz.domain;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class QuizWriter {
    private final String value;


    public QuizWriter(final String writer) {
        if (!StringUtils.hasText(writer)) throw QuizException.InvalidFormat();
        this.value = writer;
    }
}
