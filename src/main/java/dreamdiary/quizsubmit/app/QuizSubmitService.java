package dreamdiary.quizsubmit.app;

import dreamdiary.quizsubmit.domain.QuizFindResponse;
import dreamdiary.quizsubmit.domain.QuizFindService;
import dreamdiary.support.advice.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizSubmitService {
    private final QuizAnswerValidator answerValidator;
    private final QuizFindService quizFindService;

    public void submitQuiz(final Long userId, final Long quizId, final Integer answer) {
        answerValidator.validate(answer);
        // TODO 퀴즈 조회 요청(퀴즈 정답 제출 일정이 지났는지, 퀴즈 존재하는지 체크도 해야함)
        Optional<QuizFindResponse> quizFindResponseOptional = quizFindService.findQuiz(quizId);
        quizFindResponseOptional.orElseThrow(() -> RequestException.of(HttpStatus.BAD_REQUEST, "요청한 퀴즈 정보가 존재하지 않습니다."));

        // TODO 회원이 존재하는지 검사(이미 Principal 있는것이 검증이 된 것이므로 제외)
        // TODO 이미 사용자가 제출한 퀴즈 정답이 있는지 조회
        // TODO 저장
    }
}
