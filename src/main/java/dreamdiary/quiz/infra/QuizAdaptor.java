package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import dreamdiary.quiz.domain.model.QuizTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class QuizAdaptor implements QuizRepository {
    private final QuizEntityRepository quizEntityRepository;

    @Override
    public boolean isTitleAlreadyExists(final QuizTitle title) {
        return quizEntityRepository.existsByTitle(title.value());
    }

    @Override
    public Quiz store(final Quiz quiz) {
        final QuizEntity entity = QuizEntity.mapped(quiz);
        quizEntityRepository.save(entity);
        // QuizStoredEvent Publish
        return quiz;
    }

    @Override
    public QuizPublicId obtainQuizPublicId() {
        // TODO sha256도 길이 상 해시 중복이 발생할 수는 있지만, 그래도 UUID노출보단 나을듯
        // current timestamp 를 섞자
        return new QuizPublicId(UUID.randomUUID().toString());
    }

    @Override
    public Optional<Quiz> findBy(final QuizPublicId publicId) {
        return Optional.empty();
    }

    @Override
    public Optional<Quiz> findBy(final Long quizId) {
        return Optional.empty();
    }

    @Override
    public void submit(final QuizSubmit quizSubmit) {

    }
}
