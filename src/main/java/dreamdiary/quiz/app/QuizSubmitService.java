package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizRepository quizRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public void submitQuiz(final String quizPublicId, final QuizSubmitRequest request) {
        final Quiz quiz = quizRepository.findBy(new QuizPublicId(quizPublicId)).orElseThrow(QuizException::notFoundQuiz);
        final QuizSubmit quizSubmit = quiz.submit("MEMBER_PUBLIC_ID", request.getChoicePublicId());
        publisher.publishEvent(QuizSubmitGeneratedEvent.mapped(quizSubmit));
    }
}
