package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.model.QuizException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
        @Index(name = "IDX_QUIZ_SUBMIT_QUIZ_ID_MEMBER_ID", columnList = "quiz_id,member_id"),
        @Index(name = "IDX_QUIZ_SUBMIT_MEMBER_ID", columnList = "member_id"),
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
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    @Column(name = "choice_id", nullable = false)
    private Long choiceId;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    QuizSubmitEntity(final Long quizId, final Long memberId, final Long choiceId) {
        if (null == quizId || null == memberId || null == choiceId) throw QuizException.invalidFormat();
        this.quizId = quizId;
        this.memberId = memberId;
        this.choiceId = choiceId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
}
