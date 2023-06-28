package dreamdiary.quiz.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class QuizResponse {
    private String quizPublicId;
    private String title;
    private String content;
    private List<ChoiceResponse> choices;
    private LocalDateTime releaseAt;
    private LocalDateTime answerReleaseAt;
}
