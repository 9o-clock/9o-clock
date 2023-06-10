package dreamdiary.quiz.api;

import dreamdiary.quiz.app.QuizAddRequest;
import dreamdiary.quiz.app.QuizAddUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class QuizAddApi {
    private final QuizAddUseCase quizAddUseCase;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("quizzes")
    void addQuiz(@RequestBody QuizAddRequest request) {
        quizAddUseCase.addQuiz(request);
    }
}
