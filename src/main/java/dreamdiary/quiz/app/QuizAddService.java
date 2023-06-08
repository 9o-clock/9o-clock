package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.Quiz;
import dreamdiary.quiz.domain.QuizException;
import dreamdiary.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class QuizAddService implements QuizAddUseCase {
    private final QuizGenerator quizGenerator;
    private final QuizRepository quizRepository;

    @Override
    public void addQuiz(final QuizAddRequest request) {
        final Quiz quiz = quizGenerator.toQuiz(request);
        if (quizRepository.isTitleAlreadyExists(quiz.getTitle())) throw QuizException.duplicatedTitleExists();
        // 퀴즈 저장
    }
}
