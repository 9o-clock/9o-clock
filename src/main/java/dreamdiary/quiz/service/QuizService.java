package dreamdiary.quiz.service;

import dreamdiary.quiz.domain.entity.QuizEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuizService {

  //추가, 수정
  void saveQuiz(QuizEntity quiz);

  //퀴즈 아이디 기준 조회
  Optional<QuizEntity> findQuiz(int quizId);

  //출제일 기준 조회
  QuizEntity findQuiz(LocalDateTime releasedDate);

  //목록조회
  List<QuizEntity> findQuizList();

  //삭제
  void deleteQuiz(int quizId);
}
