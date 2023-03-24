package dreamdiary.quizsubmit.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizSubmitService {
    private final QuizAnswerValidator answerValidator;

    public void submitQuiz(final Long userId, final Long quizId, final Integer answer) {
        answerValidator.validate(answer);
        // TODO quizId 존재 여부 검사
        // TODO 회원이 존재하는지 검사(이미 Principal 있는것이 검증이 된 것이므로 제외)
        // TODO 이미 사용자가 제출한 퀴즈 정답이 있는지 조회
        // TODO 저장
    }
}
