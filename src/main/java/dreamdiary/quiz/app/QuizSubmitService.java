package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import dreamdiary.quiz.domain.model.SubmitterUniqId;
import dreamdiary.quiz.domain.model.exception.QuizNotFoundException;
import dreamdiary.quiz.domain.port.QuizPort;
import dreamdiary.quiz.domain.port.SubmitterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizPort quizPort;
    private final ApplicationEventPublisher publisher;
    private final SubmitterPort submitterPort;
    @Override
    public void submitQuiz(final String submitterPublicId, final String quizPublicId, final QuizSubmitRequest request) {
        final Quiz quiz = quizPort.findBy(new QuizPublicId(quizPublicId)).orElseThrow(QuizNotFoundException::new);
        final SubmitterUniqId submitterUniqId = submitterPort.verify(submitterPublicId);
        final QuizSubmit quizSubmit = quiz.submit(submitterUniqId, request.getChoicePublicId());
        publisher.publishEvent(QuizSubmitGeneratedEvent.mapped(quizSubmit));
    }
}
