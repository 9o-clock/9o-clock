package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
class QuizEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime releaseAt;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

    public static QuizEntity mapped(final Quiz quiz) {
        return builder()
                .title(quiz.getTitle().getValue())
                .content(quiz.getContent().getValue())
                .releaseAt(quiz.getReleaseAt())
                .build();
    }
}
