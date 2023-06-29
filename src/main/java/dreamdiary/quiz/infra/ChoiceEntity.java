package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.model.Choice;
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

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "QUIZ_CHOICE", indexes = {
        @Index(name = "IDX_CHOICE_PUBLIC_ID", columnList = "public_id"),
        @Index(name = "IDX_CHOICE_QUIZ_ID", columnList = "quiz_id")
})
@Entity
@Getter
class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quiz_id")
    private Long quizId;
    @Column(name = "public_id", nullable = false, unique = true)
    private String publicId;
    private String text;

    public static ChoiceEntity mapped(final Choice choice) {
        return builder()
                .text(choice.value())
                .publicId(choice.publicId())
                .build();
    }
}
