package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import dreamdiary.quiz.domain.port.QuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizPort quizPort;
    private final ApplicationEventPublisher publisher;

    @Override
    public void submitQuiz(final String quizPublicId, final QuizSubmitRequest request) {
        final Quiz quiz = quizPort.findBy(new QuizPublicId(quizPublicId)).orElseThrow(QuizException::notFoundQuiz);
        // 퀴즈를 제출해도 되는 회원인지 검증하고, 통과되면 uniqId를 얻는다.
        final QuizSubmit quizSubmit = quiz.submit("MEMBER_PUBLIC_ID", request.getChoicePublicId());
        publisher.publishEvent(QuizSubmitGeneratedEvent.mapped(quizSubmit));
    }
}
