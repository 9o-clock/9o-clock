package dreamdiary.quiz.validation;

import dreamdiary.quiz.domain.dto.QuizDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuizValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(QuizDto.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    QuizDto quizDto = (QuizDto) target;
  }
}
