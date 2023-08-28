package dreamdiary.nineoclock.infrastructure.quiz;

import dreamdiary.quiz.domain.model.SubmitterUniqId;
import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QUIZ_SUBMIT", indexes = {
        @Index(name = "IDX_QUIZ_SUBMIT_QUIZ_ID_SUBMITTER_ID", columnList = "quiz_id,submitter_id"),
        @Index(name = "IDX_QUIZ_SUBMIT_SUBMITTER_ID", columnList = "submitter_id"),
        @Index(name = "IDX_QUIZ_SUBMIT_CHOICE_ID", columnList = "choice_id")
})
@Entity
@Getter
class QuizSubmitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;
    @Convert(converter = SubmitterUniqIdConverter.class)
    @Column(name = "submitter_id", nullable = false)
    private SubmitterUniqId submitterId;
    @Column(name = "choice_id", nullable = false)
    private Long choiceId;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    QuizSubmitEntity(final Long quizId, final SubmitterUniqId submitterId, final Long choiceId) {
        if (null == quizId || null == submitterId || null == choiceId) throw new InvalidQuizFormatException();
        this.quizId = quizId;
        this.submitterId = submitterId;
        this.choiceId = choiceId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
}
