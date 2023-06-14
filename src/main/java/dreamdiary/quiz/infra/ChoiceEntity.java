package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.model.Choice;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "quiz_choice")
@Entity
@Getter
public class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public static ChoiceEntity mapped(final Choice choice) {
        return builder()
                .text(choice.getText())
                .build();
    }
}
