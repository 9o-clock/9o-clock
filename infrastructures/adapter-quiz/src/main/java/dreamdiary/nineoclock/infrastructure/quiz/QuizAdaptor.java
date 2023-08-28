package dreamdiary.nineoclock.infrastructure.quiz;

import dreamdiary.nineoclock.domain.quiz.model.Quiz;
import dreamdiary.nineoclock.domain.quiz.model.QuizPublicId;
import dreamdiary.nineoclock.domain.quiz.model.QuizSubmit;
import dreamdiary.nineoclock.domain.quiz.model.QuizTitle;
import dreamdiary.nineoclock.domain.quiz.model.exception.ChoiceNotFoundException;
import dreamdiary.nineoclock.domain.quiz.model.exception.QuizNotFoundException;
import dreamdiary.nineoclock.domain.quiz.outport.QuizOutPort;
import dreamdiary.support.cache.CacheKey;
import dreamdiary.support.cache.CacheStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
class QuizAdaptor implements QuizOutPort {
    private final QuizEntityRepository quizEntityRepository;
    private final QuizSubmitRepository quizSubmitRepository;
    private final CacheStore cacheStore;

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
        return new QuizPublicId(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public Optional<Quiz> findBy(final QuizPublicId publicId) {
        final Optional<QuizEntity> quizEntityOpt = quizEntityRepository.findByPublicId(publicId);
        return quizEntityOpt.map(QuizEntity::toQuiz);
    }

    @Override
    public Optional<Quiz> findBy(final Long quizId) {
        return quizEntityRepository.findById(quizId).map(QuizEntity::toQuiz);
    }

    @Transactional
    @Override
    public void submit(final QuizSubmit quizSubmit) {
        final CacheKey quizPublicIdCacheKey = new CacheKey(quizSubmit.quizPublicId().value());
        Map<Object, Object> quizArgsIdMap = cacheStore.findMap(quizPublicIdCacheKey);

        if (quizArgsIdMap.isEmpty()) {
            final QuizEntity quizEntity = quizEntityRepository.findByPublicId(quizSubmit.quizPublicId())
                    .orElseThrow(QuizNotFoundException::new);
            quizArgsIdMap.put(quizSubmit.quizPublicId().value(), quizEntity.getId());
            for (ChoiceEntity choice : quizEntity.getChoices()) {
                quizArgsIdMap.put(choice.getPublicId(), choice.getId());
            }
            cacheStore.storeMap(quizPublicIdCacheKey, quizArgsIdMap, 1L, TimeUnit.DAYS);
        }
        // 발생하면 안되는 장애
        if (!quizArgsIdMap.containsKey(quizSubmit.choicePublicId())) {
            cacheStore.removeKey(quizPublicIdCacheKey);
            throw new ChoiceNotFoundException();
        }
        final Long choiceId = (Long) quizArgsIdMap.get(quizSubmit.choicePublicId());
        final Long quizId = (Long) quizArgsIdMap.get(quizSubmit.quizPublicId().value());

        final QuizSubmitEntity quizSubmitEntity = new QuizSubmitEntity(quizId, quizSubmit.submitterUniqId(), choiceId);

        quizSubmitRepository.insertQuizSubmitEntity(quizSubmitEntity.getQuizId(), quizSubmitEntity.getSubmitterId().value(),
                quizSubmitEntity.getChoiceId(), quizSubmitEntity.getCreatedAt(), quizSubmitEntity.getUpdatedAt());
    }
}
