package dreamdiary.quiz.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ChoiceResponse {
    private String publicId;
    private String text;
}
