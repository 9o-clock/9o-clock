package dreamdiary.quiz.api;

import dreamdiary.credential.Credential;
import dreamdiary.credential.MemberCredential;
import dreamdiary.quiz.app.QuizSubmitRequest;
import dreamdiary.quiz.app.QuizSubmitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class QuizSubmitApi {
    private final QuizSubmitUseCase quizSubmitUseCase;

    @PostMapping("quizzes/{quizPublicId}/submit")
    void submitQuiz(@Credential MemberCredential memberCredential,
                    @PathVariable String quizPublicId,
                    @RequestBody QuizSubmitRequest request) {
        quizSubmitUseCase.submitQuiz(memberCredential.memberPublicId(), quizPublicId, request);
    }
}
