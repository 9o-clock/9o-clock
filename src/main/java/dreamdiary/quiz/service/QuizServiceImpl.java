package dreamdiary.quiz.service;

import dreamdiary.quiz.domain.entity.QuizEntity;
import dreamdiary.quiz.repository.QuizRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
  private final QuizRepository quizRepo;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void saveQuiz(QuizEntity quiz) {
    //TODO 중복여부 확인할
    quizRepo.save(quiz);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<QuizEntity> findQuiz(int quizId) {
    return quizRepo.findById(quizId);
  }

  //TODO 출제일에 해당하는 문제가 하나일지 혹은 리스트일지 확인 필요
  @Override
  public QuizEntity findQuiz(LocalDateTime releasedDate) {
    return quizRepo.findByReleasedDate(releasedDate);
  }

  @Override
  public List<QuizEntity> findQuizList() {
    return quizRepo.findAll();
  }

  @Override
  public void deleteQuiz(int quizId) {
    quizRepo.deleteById(quizId);
  }
}
