package dreamdiary.quiz.query;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.infra.QChoiceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static dreamdiary.quiz.infra.QQuizEntity.quizEntity;

@RequiredArgsConstructor
@Repository
public class QuizQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    @Cacheable(value = "quizzes", key = "#publicId")
    public Optional<QuizDto> findOne(final String publicId) {
        final List<QuizDto> transform = queryFactory.select(quizEntity)
                .from(quizEntity)
                .leftJoin(QChoiceEntity.choiceEntity)
                .on(quizEntity.id.eq(QChoiceEntity.choiceEntity.quizId))
                .where(quizEntity.publicId.eq(new QuizPublicId(publicId)))
                .distinct()
                .transform(
                        GroupBy.groupBy(quizEntity.id)
                                .list(
                                        Projections.fields(QuizDto.class,
                                                Expressions.asString(publicId).as("publicId"),
                                                quizEntity.title,
                                                quizEntity.content,
                                                quizEntity.releaseAt,
                                                quizEntity.answerReleaseAt,
                                                GroupBy.list(
                                                        Projections.fields(ChoiceDto.class,
                                                                QChoiceEntity.choiceEntity.publicId,
                                                                QChoiceEntity.choiceEntity.text
                                                        )
                                                ).as("choices")
                                        )
                                )
                );

        if (transform.isEmpty()) return Optional.empty();
        return Optional.of(transform.get(0));
    }
}
