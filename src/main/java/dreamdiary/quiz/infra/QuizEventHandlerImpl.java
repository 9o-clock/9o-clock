package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizEventHandler;
import dreamdiary.quiz.domain.event.QuizGeneratedEvent;
import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;
import dreamdiary.quiz.domain.port.QuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class QuizEventHandlerImpl implements QuizEventHandler {
    private final QuizPort quizPort;

    @Async
    @EventListener(QuizGeneratedEvent.class)
    @Override
    public void handle(final QuizGeneratedEvent event) {
        quizPort.store(event.quiz());
    }

    @Async
    @EventListener(QuizSubmitGeneratedEvent.class)
    @Override
    public void handle(final QuizSubmitGeneratedEvent event) {
        quizPort.submit(event.quizSubmit());
    }
}
