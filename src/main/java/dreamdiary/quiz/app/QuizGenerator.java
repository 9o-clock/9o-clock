package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.Choice;
import dreamdiary.quiz.domain.Choices;
import dreamdiary.quiz.domain.Quiz;
import dreamdiary.quiz.domain.QuizContent;
import dreamdiary.quiz.domain.QuizTitle;
import dreamdiary.quiz.domain.QuizWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class QuizGenerator {
     Quiz toQuiz(final QuizAddRequest request) {
        final QuizWriter writer = new QuizWriter("writer");
        final QuizTitle title = new QuizTitle(request.getTitle());
        final QuizContent content = new QuizContent(request.getContent());
        final Choices choices = new Choices(new Choice("1번"), new Choice("2번"));
        final LocalDateTime releaseAt = LocalDateTime.now();

        return Quiz.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .choices(choices)
                .releaseAt(releaseAt)
                .build();
    }
}
