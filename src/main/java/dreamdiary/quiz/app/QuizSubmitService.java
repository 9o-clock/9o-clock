package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizRepository quizRepository;
    @Override
    public void submitQuiz(final String quizPublicId, final QuizSubmitRequest request) {
        final Quiz quiz = quizRepository.findBy(new QuizPublicId(quizPublicId)).orElseThrow(QuizException::notFoundQuiz);
        // 퀴즈에 해당 선택지가 있는지 검사
        // 정답을 제출할 수 있는 일자 및 시간인지 검사
        // 위의 검증 케이스를 Quiz 객체에게 넘김 (제출해도 되는지 여부 물어보기)


        // 정답 제출 이벤트 발생

        // 퀴즈 제출 이력을 검사할 때 quizId, choiceId 인덱스로 만들어두어야함.
    }
}
