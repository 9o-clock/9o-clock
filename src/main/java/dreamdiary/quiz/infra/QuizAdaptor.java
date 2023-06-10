package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.Quiz;
import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.QuizTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class QuizAdaptor implements QuizRepository {
    private final QuizEntityRepository quizEntityRepository;

    @Override
    public boolean isTitleAlreadyExists(final QuizTitle title) {
        return quizEntityRepository.existsByTitle(title.getValue());
    }

    @Override
    public Quiz store(final Quiz quiz) {
        final QuizEntity entity = QuizEntity.mapped(quiz);
        quizEntityRepository.save(entity);
        // QuizStoredEvent Publish
        return quiz;
    }
}
