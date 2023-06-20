package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizSubmit;
import dreamdiary.quiz.domain.model.QuizTitle;
import dreamdiary.support.cache.CacheKey;
import dreamdiary.support.cache.CacheStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
class QuizAdaptor implements QuizRepository {
    private final QuizEntityRepository quizEntityRepository;
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
        Optional<Object> quizIdOpt = cacheStore.findData(new CacheKey(quizSubmit.quizId().value()));
        if (quizIdOpt.isEmpty()) {
            // 퀴즈 객체 조회
            // 없으면 예외처리
            // cache 처리
            // quizIdOpt 값 덮어쓰기
        }
        // member, 퀴즈, 선택지 조회
        // TODO 해당 내용 중 quiz, choice는 여러 트랜젝션에서 불필요한 중복쿼리가 발생될텐데 이걸 캐시처리하는게 맞을 것 같다.
        // TODO 2차캐시를 쓰던 Redis를 쓰던 해야할 것 같고, 제출 처리 과정이 복잡하니 추후 비동기로 빼기 위해 submit 호출을 이벤트로 변경해야겠다.
    }
}
