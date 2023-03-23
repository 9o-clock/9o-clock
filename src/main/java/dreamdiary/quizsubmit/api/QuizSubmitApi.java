package dreamdiary.quizsubmit.api;

import dreamdiary.quizsubmit.app.QuizSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizSubmitApi {
    // TODO 내가 제출한 퀴즈  보기
    private final QuizSubmitService quizSubmitService;

    @PostMapping("quizzes/{quizId}/submissions")
    public void submitQuiz(@PathVariable(name = "quizId") Long quizId, @RequestParam("answer") Long answer) {
        quizSubmitService.submitQuiz(quizId, answer);
    }
}
