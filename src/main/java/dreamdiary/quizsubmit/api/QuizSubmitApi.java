package dreamdiary.quizsubmit.api;

import dreamdiary.quizsubmit.app.QuizSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class QuizSubmitApi {
    // TODO 내가 제출한 퀴즈  보기
    private final QuizSubmitService quizSubmitService;

    @PostMapping("quizzes/{quizId}/submissions")
    public void submitQuiz(Principal principal, @PathVariable(name = "quizId") Long quizId, @RequestParam(value = "answer", required = false) Integer answer) {
        // TODO null 체크에 대한 책임을 누가 갖을지 검토
        // TODO 문자열 유니크 값으로 로그인을 관리하면 filter 과정에서 체크될듯
        String userId = principal.getName();
        quizSubmitService.submitQuiz(Long.valueOf(userId), quizId, answer);
    }
}
