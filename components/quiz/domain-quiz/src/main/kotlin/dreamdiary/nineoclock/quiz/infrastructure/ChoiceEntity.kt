package dreamdiary.nineoclock.quiz.infrastructure

import dreamdiary.nineoclock.quiz.domain.Choice
import dreamdiary.nineoclock.shard.identifier.ChoiceId
import dreamdiary.nineoclock.shard.identifier.ChoicePublicId
import dreamdiary.nineoclock.shard.identifier.ChoiceSecureId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "quiz_choice",
    indexes = [
        Index(name = "IDX_CHOICE_PUBLIC_ID", columnList = "public_id"),
        Index(name = "IDX_CHOICE_SECURE_ID", columnList = "secure_id"),
        Index(name = "IDX_CHOICE_QUIZ_ID", columnList = "quiz_id"),
    ]
)
@Entity
internal class ChoiceEntity(
    @Column(name = "public_id", nullable = false, unique = true)
    val publicId: String,
    @Column(name = "secure_id", nullable = false, unique = true)
    val secureId: String,
    @Column(name = "text")
    val text: String,
    @Column(name = "isAnswer")
    val isAnswer: Boolean,
    @CreatedDate @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)

internal fun Choice.toEntity(): ChoiceEntity {
    return ChoiceEntity(
        publicId = this.id.publicId.value,
        secureId = this.id.secureId.value,
        text = this.value,
        isAnswer = this.isAnswer
    )
}

internal fun ChoiceEntity.toModel() : Choice {
    return Choice(
        value = this.text,
        isAnswer = this.isAnswer,
        id = ChoiceId(
            publicId = ChoicePublicId(this.publicId),
            secureId = ChoiceSecureId(this.secureId)
        ),
    )
}
