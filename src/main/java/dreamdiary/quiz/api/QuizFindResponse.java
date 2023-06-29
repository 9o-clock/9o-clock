package dreamdiary.quiz.api;

import dreamdiary.quiz.query.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizFindResponse {
    private final QuizDto quiz;
}
