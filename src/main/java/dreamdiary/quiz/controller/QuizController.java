package dreamdiary.quiz.controller;

import dreamdiary.quiz.domain.dto.QuizDto;
import dreamdiary.quiz.domain.entity.QuizEntity;
import dreamdiary.quiz.service.QuizService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("quiz")
public class QuizController {
  private final QuizService quizService;

  @PostMapping
  public ResponseEntity<Object> addQuiz(@RequestBody QuizDto quiz) {
    //TODO image 데이터 추가
    quizService.saveQuiz(quiz);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuizDto> getQuizById(@PathVariable int quizId) {
    Optional<QuizEntity> quiz = quizService.findQuiz(quizId);
    if(quiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
      return new ResponseEntity<>(quiz.get().toDto(), HttpStatus.OK);
  }

  @GetMapping("/{date}")
  public ResponseEntity<QuizDto> getQuizByReleasedDate(@PathVariable LocalDateTime releasedDate) {
    Optional<QuizEntity> quiz = quizService.findQuiz(releasedDate);
    if(quiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(quiz.get().toDto(), HttpStatus.OK);
  }

  @GetMapping("list")
  public ResponseEntity<List<QuizDto>> getQuizAll() {
    List<QuizEntity> quizEntityList =  quizService.findQuizList();
    List<QuizDto> quizdtoList = new ArrayList<>();

    for(QuizEntity quizEntity : quizEntityList) {
      quizdtoList.add(quizEntity.toDto());
    }
    return new ResponseEntity<>(quizdtoList, HttpStatus.OK);
  }

  @DeleteMapping("{/{id}")
  public ResponseEntity<QuizDto> removeQuiz(@PathVariable int quizId) {
    quizService.deleteQuiz(quizId);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
