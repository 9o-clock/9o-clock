package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.event.QuizGeneratedEvent;
import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.Choices;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizContent;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;
import dreamdiary.quiz.domain.model.exception.DuplicatedTitleExistsException;
import dreamdiary.quiz.domain.port.QuizPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class QuizAddService implements QuizAddUseCase {
    private final QuizPort quizPort;
    private final ApplicationEventPublisher publisher;

    @Override
    public void addQuiz(final QuizAddRequest request) {
        final Quiz quiz = generateQuiz(request);
        if (quizPort.isTitleAlreadyExists(quiz.getTitle())) throw new DuplicatedTitleExistsException();
        publisher.publishEvent(QuizGeneratedEvent.mapped(quiz));
    }

    private Quiz generateQuiz(final QuizAddRequest request) {
        final QuizTitle title = new QuizTitle(request.getTitle());
        final QuizContent content = new QuizContent(request.getContent());
        final List<Choice> choiceList = request.getChoices()
                .stream()
                .map(Choice::new)
                .toList();

        final Choices choices = new Choices(choiceList);
        final QuizPublicId quizPublicId = quizPort.obtainQuizPublicId();

        return Quiz.builder()
                .quizPublicId(quizPublicId)
                .title(title)
                .content(content)
                .choices(choices)
                .releaseAt(request.getReleaseAt())
                .answerReleaseAt(request.getAnswerReleaseAt())
                .build();
    }
}
