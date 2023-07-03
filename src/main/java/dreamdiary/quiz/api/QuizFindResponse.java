package dreamdiary.quiz.api;

import dreamdiary.quiz.query.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class QuizFindResponse {
    private final QuizDto quiz;
}
