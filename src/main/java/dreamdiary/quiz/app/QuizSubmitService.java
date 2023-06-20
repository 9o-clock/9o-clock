package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizRepository quizRepository;
    @Override
    public void submitQuiz(final String quizPublicId, final QuizSubmitRequest request) {
        final Quiz quiz = quizRepository.findBy(new QuizPublicId(quizPublicId)).orElseThrow(QuizException::notFoundQuiz);
        final QuizSubmit quizSubmit = quiz.submit(null, request.getChoiceId());
        quizRepository.submit(quizSubmit);
        // 정답 제출 이벤트 발생
        // 퀴즈 제출 이력을 검사할 때 quizPublicId, choicePublicId 인덱스로 만들어두어야함.
    }
}
