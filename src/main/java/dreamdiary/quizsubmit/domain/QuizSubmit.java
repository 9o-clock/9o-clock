package dreamdiary.quizsubmit.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class QuizSubmit {
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long quizId;
    @Column(nullable = false)
    private Integer answer;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime registerAt;

    private QuizSubmit(final Long userId,final Long quizId,  final Integer answer, final Long id, final LocalDateTime registerAt) {
        this.quizId = quizId;
        this.userId = userId;
        this.answer = answer;
        this.id = id;
        this.registerAt = registerAt;
    }

    public static QuizSubmit create(final Long userId,final Long quizId,  final Integer answer, final QuizSubmitRepository quizSubmitRepository) {
        final QuizSubmit quizSubmit = new QuizSubmit(userId, quizId, answer, null, null);
        quizSubmitRepository.save(quizSubmit);
        return quizSubmit;
    }
}
