package dreamdiary.quiz.domain.entity;

import dreamdiary.quiz.domain.dto.QuizDto;
import dreamdiary.support.jpa.DateAutoGenerator;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class QuizEntity extends DateAutoGenerator {
  @GeneratedValue(strategy =  GenerationType.AUTO)
  @Id
  private Integer quizId;

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

  @Future
  private LocalDateTime releasedDate;

  public QuizDto toDto() {
    return QuizDto.builder().title(this.title)
        .description(this.description)
        .answer(this.answer)
        .optNumOne(optNumOne)
        .optNumTwo(this.optNumTwo)
        .optNumThree(this.optNumThree)
        .optNumFour(this.optNumFour)
        .releasedDate(this.releasedDate)
        .build();
  }
}
