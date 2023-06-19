package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        int retryCount = 0;
        while (retryCount < 15) {
            retryCount++;
            UUID uuid = UUID.randomUUID();
            if (!quizEntityRepository.existsByPublicId(uuid.toString())) {
                return new QuizPublicId(uuid.toString());
            }
        }
        throw new RuntimeException(); // 시스템 장애인데 이거 이따구 구현 맞어? ㅋㅋ...
    }
}
