package dreamdiary.quiz.api;

import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.query.QuizQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class QuizQueryApi {
    private final QuizQueryDslRepository quizQueryDslRepository;

    @GetMapping("quizzes/{publicId}")
    QuizFindResponse findQuiz(@PathVariable String publicId) {
        return quizQueryDslRepository.findOne(publicId)
                .map(QuizFindResponse::new)
                .orElseThrow(QuizException::notFoundQuiz);
    }

    /**
     * 퀴즈 정보 조회(아이디 기준)
     * 달 기준 퀴즈 아이디 목록 조회(아이디만 반환??)
     * 퀴즈 참여 정보 조회
     */
}
