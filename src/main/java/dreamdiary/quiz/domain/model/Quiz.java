package dreamdiary.quiz.domain.model;

import dreamdiary.quiz.domain.model.exception.ChoiceNotFoundException;
import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import dreamdiary.quiz.domain.model.exception.NotSubmitAtException;
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
    public Quiz(final QuizPublicId quizPublicId, final QuizTitle title, final QuizContent content,
                final Choices choices, final LocalDateTime releaseAt, final LocalDateTime answerReleaseAt) {
        if (null == quizPublicId || null == title || null == content) throw new InvalidQuizFormatException();
        if (null == choices || null == releaseAt || null == answerReleaseAt) throw new InvalidQuizFormatException();
        if (!answerReleaseAt.isAfter(releaseAt)) throw new InvalidQuizFormatException();
        this.quizPublicId = quizPublicId;
        this.title = title;
        this.content = content;
        this.choices = choices;
        this.releaseAt = releaseAt;
        this.answerReleaseAt = answerReleaseAt;
    }

    public QuizSubmit submit(final SubmitterUniqId submitterUniqId, final String choicePublicId) {
        if (null == submitterUniqId) throw new InvalidQuizFormatException();
        if (!StringUtils.hasText(choicePublicId)) throw new InvalidQuizFormatException();
        if (this.choices.values().stream().filter(choice -> Objects.equals(choice.publicId(), choicePublicId)).findFirst().isEmpty())
            throw new ChoiceNotFoundException();
        if (this.releaseAt.isAfter(LocalDateTime.now())) throw new NotSubmitAtException();
        return new QuizSubmit(this.quizPublicId, submitterUniqId, choicePublicId);
    }
}
