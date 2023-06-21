package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizEventHandler;
import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.event.QuizGeneratedEvent;
import dreamdiary.quiz.domain.event.QuizSubmitGeneratedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class QuizEventHandlerImpl implements QuizEventHandler {
    private final QuizRepository quizRepository;

    @EventListener(QuizGeneratedEvent.class)
    @Override
    public void handle(final QuizGeneratedEvent event) {
        quizRepository.store(event.quiz());
    }

    @EventListener(QuizSubmitGeneratedEvent.class)
    @Override
    public void handle(final QuizSubmitGeneratedEvent event) {
        quizRepository.submit(event.quizSubmit());
    }
}
