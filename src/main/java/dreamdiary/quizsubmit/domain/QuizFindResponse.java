package dreamdiary.quizsubmit.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class QuizFindResponse {
    private Long quizId;
    private String title;
    private LocalDate releaseDate;
}
