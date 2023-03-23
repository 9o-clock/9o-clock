package dreamdiary.quiz.domain.dto;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class QuizDto {
  private String title;
  private String description;
  private int answer;

  //문항 1,2,3,4
  private String optNumOne;
  private String optNumTwo;
  private String optNumThree;
  private String optNumFour;

  private LocalDateTime releasedDate;
}
