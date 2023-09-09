package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.model.Choice
import dreamdiary.nineoclock.quiz.domain.model.ChoiceGroup
import dreamdiary.nineoclock.quiz.domain.model.Quiz
import dreamdiary.nineoclock.quiz.domain.model.QuizContent
import dreamdiary.nineoclock.quiz.domain.model.QuizTitle
import dreamdiary.nineoclock.shard.identifier.QuizId
import dreamdiary.nineoclock.shard.identifier.QuizPublicId
import dreamdiary.nineoclock.shard.identifier.QuizSecureId
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.BatchSize
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@BatchSize(size = 10)
@Table(
    name = "quiz",
    indexes = [
        Index(name = "IDX_QUIZ_PUBLIC_ID", columnList = "public_id"),
        Index(name = "IDX_QUIZ_SECURE_ID", columnList = "secure_id"),
    ]
)
@Entity
internal class QuizEntity(
    @Column(name = "public_id", nullable = false, unique = true)
    val publicId: String,
    @Column(name = "secure_id", nullable = false, unique = true)
    val secureId: String,
    @Column(name = "title")
    val title: String,
    @Column(name = "content")
    val content: String,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    val choices: List<ChoiceEntity> = mutableListOf(),
    @Column(name = "release_at")
    val releaseAt: LocalDateTime,
    @Column(name = "answer_release_at")
    val answerReleaseAt: LocalDateTime,
    @CreatedDate @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)

internal fun Quiz.toEntity(): QuizEntity {
    return QuizEntity(
        publicId = this.id.publicId.value,
        secureId = this.id.secureId.value,
        title = this.title.value,
        content = this.content.value,
        choices = this.choiceGroup.values.map(Choice::toEntity),
        releaseAt = this.releaseAt,
        answerReleaseAt = this.answerReleaseAt
    )
}

internal fun QuizEntity.toModel() : Quiz {
    return Quiz(
        title = QuizTitle(this.title),
        content = QuizContent(this.content),
        choiceGroup = ChoiceGroup(
            values = this.choices.map { it.toModel() }
        ),
        releaseAt = this.releaseAt,
        answerReleaseAt = this.answerReleaseAt,
        id = QuizId(
            publicId = QuizPublicId(this.publicId),
            secureId = QuizSecureId(this.secureId)
        )
    )
}
