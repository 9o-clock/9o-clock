package dreamdiary.quiz.query;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuizDto {
    private String publicId;
    private String title;
    private String content;
    private List<ChoiceDto> choices;
    private LocalDateTime releaseAt;
    private LocalDateTime answerReleaseAt;

    public void join(List<ChoiceDto> list) {
        this.choices = list;
    }
}
