package dreamdiary.quizsubmit.api;

import dreamdiary.quizsubmit.app.QuizSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class QuizSubmitApi {
    // TODO 내가 제출한 퀴즈  보기
    @GetMapping("quizzes/{quizId}/submissions")
    public void getSubmitQuiz(Principal principal, @PathVariable(name = "quizId") Long quizId) {
        String userId = principal.getName();
    }
    private final QuizSubmitService quizSubmitService;

    @PostMapping("quizzes/{quizId}/submissions")
    public void submitQuiz(Principal principal, @PathVariable(name = "quizId") Long quizId, @RequestParam(value = "answer", required = false) Integer answer) {
        String userId = principal.getName();
        quizSubmitService.submitQuiz(Long.valueOf(userId), quizId, answer);
    }
}
