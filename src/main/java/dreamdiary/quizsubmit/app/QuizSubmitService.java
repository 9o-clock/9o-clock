package dreamdiary.quizsubmit.app;

import dreamdiary.quizsubmit.domain.QuizFindResponse;
import dreamdiary.quizsubmit.domain.QuizFindService;
import dreamdiary.quizsubmit.domain.QuizSubmit;
import dreamdiary.quizsubmit.domain.QuizSubmitRepository;
import dreamdiary.support.advice.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuizSubmitService {
    private final QuizAnswerValidator answerValidator;
    private final QuizFindService quizFindService;

    private final QuizSubmitRepository quizSubmitRepository;

    public void submitQuiz(final Long userId, final Long quizId, final Integer answer) {
        answerValidator.validate(answer);
        Optional<QuizFindResponse> quizFindResponseOptional = quizFindService.findQuiz(quizId);
        final QuizFindResponse quizFindResponse = quizFindResponseOptional.orElseThrow(() -> RequestException.of(HttpStatus.BAD_REQUEST, "요청한 퀴즈 정보가 존재하지 않습니다."));
        if (null == quizFindResponse.getReleaseDate() || !quizFindResponse.getReleaseDate().isEqual(LocalDate.now()))
            throw RequestException.of(HttpStatus.BAD_REQUEST, "해당 퀴즈는 정답을 제출할 수 없습니다.");
        if (quizSubmitRepository.existsQuizSubmitByUserIdAndQuizId(userId, quizId))
            throw RequestException.of(HttpStatus.BAD_REQUEST, "이미 해당 퀴즈의 정답을 제출하였습니다.");
        QuizSubmit.create(userId, quizId, answer, quizSubmitRepository);
    }
}
