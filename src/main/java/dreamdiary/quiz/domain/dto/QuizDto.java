package dreamdiary.quiz.domain.dto;


import dreamdiary.quiz.domain.entity.QuizEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class QuizDto {
  @NonNull
  private String title;
  @NonNull
  private String description;
  @NonNull
  private int answer;

  //문항 1,2,3,4
  @NonNull
  private String optNumOne;
  @NonNull
  private String optNumTwo;
  @Nullable
  private String optNumThree;
  @Nullable
  private String optNumFour;

  // TODO 미래 날짜만 가능, 형식 지정
  @Nullable
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime releasedDate;

  public QuizEntity toEntity() {
    return QuizEntity.builder().title(this.title)
        .description(this.description)
        .answer(this.answer)
        .optNumOne(this.optNumOne)
        .optNumTwo(this.optNumTwo)
        .optNumThree(this.optNumThree)
        .optNumFour(this.optNumFour)
        .releasedDate(this.releasedDate)
        .build();
  }
}
