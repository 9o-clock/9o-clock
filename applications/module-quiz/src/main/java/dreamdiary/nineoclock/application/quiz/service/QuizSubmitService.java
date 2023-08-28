package dreamdiary.nineoclock.application.quiz.service;

import dreamdiary.nineoclock.application.quiz.usecase.QuizSubmitCommand;
import dreamdiary.nineoclock.application.quiz.usecase.QuizSubmitUseCase;
import dreamdiary.nineoclock.domain.quiz.model.Quiz;
import dreamdiary.nineoclock.domain.quiz.model.QuizPublicId;
import dreamdiary.nineoclock.domain.quiz.model.QuizSubmit;
import dreamdiary.nineoclock.domain.quiz.model.SubmitterUniqId;
import dreamdiary.nineoclock.domain.quiz.model.exception.QuizNotFoundException;
import dreamdiary.nineoclock.domain.quiz.outport.QuizOutPort;
import dreamdiary.nineoclock.domain.quiz.outport.SubmitterOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizSubmitService implements QuizSubmitUseCase {
    private final QuizOutPort quizOutPort;
    private final SubmitterOutPort submitterOutPort;

    @Override
    public void submitQuiz(QuizSubmitCommand command) {
        final Quiz quiz = quizOutPort.findBy(new QuizPublicId(command.quizPublicId())).orElseThrow(QuizNotFoundException::new);
        final SubmitterUniqId submitterUniqId = submitterOutPort.verify(command.submitterPublicId());
        final QuizSubmit quizSubmit = quiz.submit(submitterUniqId, command.choicePublicId());
        quizOutPort.submit(quizSubmit);
    }
}
