package dreamdiary.quiz.service;

import dreamdiary.quiz.domain.dto.QuizDto;
import dreamdiary.quiz.domain.entity.QuizEntity;
import dreamdiary.quiz.repository.QuizRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepo;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void saveQuiz(QuizDto quiz) {
    //TODO 중복여부 확인할
    try {
      quizRepo.save(quiz.toEntity());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<QuizEntity> findQuiz(int quizId) {
    try {
      return quizRepo.findById(quizId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  //TODO 출제일에 해당하는 문제가 하나일지 혹은 리스트일지 확인 필요
  @Override
  public Optional<QuizEntity> findQuiz(LocalDateTime releasedDate) {
    try {
      return quizRepo.findByReleasedDate(releasedDate);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public List<QuizEntity> findQuizList() {
    try {
      return quizRepo.findAll();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public void deleteQuiz(int quizId) {
    try {
      quizRepo.deleteById(quizId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }
}