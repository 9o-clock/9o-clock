package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizEventHandler;
import dreamdiary.quiz.domain.QuizGeneratedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class QuizEventHandlerImpl implements QuizEventHandler {
    @Override
    public void handle(final QuizGeneratedEvent event) {

    }
}
