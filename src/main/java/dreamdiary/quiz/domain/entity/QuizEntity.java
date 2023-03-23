package dreamdiary.quiz.domain.entity;

import dreamdiary.quiz.domain.dto.QuizDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@Entity
@Table(name="tb_quiz")
public class QuizEntity {
  @GeneratedValue(strategy =  GenerationType.AUTO)
  @Id
  private Long quizId;

  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private int answer;

  //문항 1,2,3,4
  @Column(nullable = false)
  private String optNumOne;
  @Column(nullable = false)
  private String optNumTwo;
  private String optNumThree;
  private String optNumFour;

  private LocalDateTime releasedDate;

  @CreatedDate
  private LocalDateTime registeredDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  public QuizDto toDto() {
    return QuizDto.builder().title(this.title)
        .description(this.description)
        .answer(this.answer)
        .optNumOne(optNumOne)
        .optNumTwo(this.optNumTwo)
        .optNumThree(this.optNumThree)
        .optNumFour(this.optNumFour)
        .build();
  }
}
