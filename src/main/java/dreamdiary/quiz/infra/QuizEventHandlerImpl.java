package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizEventHandler;
import dreamdiary.quiz.domain.QuizGeneratedEvent;
import dreamdiary.quiz.domain.QuizRepository;
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
        quizRepository.store(event.getQuiz());
    }
}
