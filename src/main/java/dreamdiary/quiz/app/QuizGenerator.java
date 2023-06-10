package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.Choice;
import dreamdiary.quiz.domain.Choices;
import dreamdiary.quiz.domain.Quiz;
import dreamdiary.quiz.domain.QuizContent;
import dreamdiary.quiz.domain.QuizTitle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class QuizGenerator {
    Quiz toQuiz(final QuizAddRequest request) {
        final QuizTitle title = new QuizTitle(request.getTitle());
        final QuizContent content = new QuizContent(request.getContent());
        final Choice[] choiceArray = request.getChoices()
                .stream()
                .map(Choice::new)
                .toArray(Choice[]::new);

        final Choices choices = new Choices(choiceArray);
        final LocalDateTime releaseAt = request.getReleaseAt();

        return Quiz.builder()
                .title(title)
                .content(content)
                .choices(choices)
                .releaseAt(releaseAt)
                .build();
    }
}
