package dreamdiary.nineoclock.application.quiz.service;

import dreamdiary.nineoclock.application.quiz.usecase.QuizAddCommand;
import dreamdiary.nineoclock.application.quiz.usecase.QuizAddUseCase;
import dreamdiary.nineoclock.domain.quiz.model.Choice;
import dreamdiary.nineoclock.domain.quiz.model.Choices;
import dreamdiary.nineoclock.domain.quiz.model.Quiz;
import dreamdiary.nineoclock.domain.quiz.model.QuizContent;
import dreamdiary.nineoclock.domain.quiz.model.QuizPublicId;
import dreamdiary.nineoclock.domain.quiz.model.QuizTitle;
import dreamdiary.nineoclock.domain.quiz.model.exception.DuplicatedTitleExistsException;
import dreamdiary.nineoclock.domain.quiz.outport.QuizOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class QuizAddService implements QuizAddUseCase {
    private final QuizOutPort quizOutPort;

    @Override
    public void addQuiz(final QuizAddCommand request) {
        final Quiz quiz = generateQuiz(request);
        if (quizOutPort.isTitleAlreadyExists(quiz.getTitle())) throw new DuplicatedTitleExistsException();
        quizOutPort.save(quiz);
    }

    private Quiz generateQuiz(final QuizAddCommand request) {
        final QuizTitle title = new QuizTitle(request.title());
        final QuizContent content = new QuizContent(request.content());
        final List<Choice> choiceList = request.choices()
                .stream()
                .map(Choice::new)
                .toList();

        final Choices choices = new Choices(choiceList);
        final QuizPublicId quizPublicId = quizOutPort.obtainQuizPublicId();

        return Quiz.builder()
                .quizPublicId(quizPublicId)
                .title(title)
                .content(content)
                .choices(choices)
                .releaseAt(request.releaseAt())
                .answerReleaseAt(request.answerReleaseAt())
                .build();
    }
}
