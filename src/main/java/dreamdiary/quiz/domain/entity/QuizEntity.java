package dreamdiary.quiz.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  @Builder.Default
  @CreatedDate
  private LocalDateTime registeredDate;

  @Builder.Default
  @LastModifiedDate
  private LocalDateTime modifiedDate;
}
