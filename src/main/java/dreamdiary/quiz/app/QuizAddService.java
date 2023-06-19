package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizGeneratedEvent;
import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizAddService implements QuizAddUseCase {
    private final QuizGenerator quizGenerator;
    private final QuizRepository quizRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public void addQuiz(final QuizAddRequest request) {
        final Quiz quiz = quizGenerator.generateQuiz(request);
        if (quizRepository.isTitleAlreadyExists(quiz.getTitle())) throw QuizException.duplicatedTitleExists();
        publisher.publishEvent(QuizGeneratedEvent.mapped(quiz));
    }
}
