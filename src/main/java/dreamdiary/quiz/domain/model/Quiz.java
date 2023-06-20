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

    /**
     * 제출 가능한지
     * 선택지 아이디가 존재해야하고,
     * 음... 제출일자가 지났으면 통계반영만 안되도록할건데 아닌가 하나하나 전부 정보인데 해야하나
     * 시간을 제한할 필요가 있나..?
     * 출제 일시만 알려주고 딱히 시간 제한이 필요해보이진 않긴한데 통계는 해야하고
     * 음 정답 공개일시라고 하는게 맞겠는데 제출은 언제해도 관계없고
     *
     */
}
