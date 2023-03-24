package dreamdiary.quiz.controller;

import dreamdiary.quiz.domain.dto.QuizDto;
import dreamdiary.quiz.domain.entity.QuizEntity;
import dreamdiary.quiz.service.QuizService;
import dreamdiary.quiz.validation.QuizValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {
  private final QuizService quizService;
  private final QuizValidator quizValidator;

  @PostMapping
  public ResponseEntity<Object> addQuiz(@Validated @RequestBody QuizDto quiz, BindingResult bindingResult) {
    quizValidator.validate(quiz, bindingResult);
    if (bindingResult.hasErrors()) {
      //TODO 에러 반환 처리
//      return bindingResult.getFieldErrors();
    }

    //TODO image 데이터 추가
    quizService.saveQuiz(quiz);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping("/{quizId}")
  public ResponseEntity<QuizDto> getQuizById(@PathVariable("quizId") int quizId) {
    Optional<QuizEntity> quiz = quizService.findQuiz(quizId);
    if(quiz.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
      return new ResponseEntity<>(quiz.get().toDto(), HttpStatus.OK);
  }

//  @GetMapping("/{releasedDate}")
//  public ResponseEntity<QuizDto> getQuizByReleasedDate(@PathVariable("releasedDate") LocalDateTime releasedDate) {
//    Optional<QuizEntity> quiz = quizService.findQuiz(releasedDate);
//    if(quiz.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    return new ResponseEntity<>(quiz.get().toDto(), HttpStatus.OK);
//  }

  @GetMapping("list")
  public ResponseEntity<List<QuizDto>> getQuizAll() {
    List<QuizEntity> quizEntityList =  quizService.findQuizList();
    List<QuizDto> quizdtoList = new ArrayList<>();

    for(QuizEntity quizEntity : quizEntityList) {
      quizdtoList.add(quizEntity.toDto());
    }
    return new ResponseEntity<>(quizdtoList, HttpStatus.OK);
  }

  @DeleteMapping("/{quizId}")
  public ResponseEntity<QuizDto> removeQuiz(@PathVariable("quizId") Integer quizId) {
    //TODO exception 발생하는 경우 응답값 결과 처리 수정
    quizService.deleteQuiz(quizId);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
