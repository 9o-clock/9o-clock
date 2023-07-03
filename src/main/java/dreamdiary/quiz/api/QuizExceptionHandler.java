package dreamdiary.quiz.api;

import dreamdiary.quiz.domain.model.exception.ChoiceNotFoundException;
import dreamdiary.quiz.domain.model.exception.DuplicatedTitleExistsException;
import dreamdiary.quiz.domain.model.exception.InvalidQuizFormatException;
import dreamdiary.quiz.domain.model.exception.NotSubmitAtException;
import dreamdiary.quiz.domain.model.exception.QuizNotFoundException;
import dreamdiary.quiz.domain.model.exception.SubmitterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class QuizExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    ResponseEntity<String> handle(final QuizNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChoiceNotFoundException.class)
    ResponseEntity<String> handle(final ChoiceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubmitterNotFoundException.class)
    ResponseEntity<String> handle(final SubmitterNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidQuizFormatException.class)
    ResponseEntity<String> handle(final InvalidQuizFormatException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedTitleExistsException.class)
    ResponseEntity<String> handle(final DuplicatedTitleExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotSubmitAtException.class)
    ResponseEntity<String> handle(final NotSubmitAtException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
