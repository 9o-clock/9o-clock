package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.Choices;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizContent;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
class QuizGenerator {
    private final QuizRepository quizRepository;

    Quiz generateQuiz(final QuizAddRequest request) {
        final QuizTitle title = new QuizTitle(request.getTitle());
        final QuizContent content = new QuizContent(request.getContent());
        List<Choice> choiceList = request.getChoices()
                .stream()
                .map(Choice::new)
                .toList();

        final Choices choices = new Choices(choiceList);
        final LocalDateTime releaseAt = request.getReleaseAt();
        QuizPublicId quizPublicId = quizRepository.obtainQuizPublicId();

        return Quiz.builder()
                .quizPublicId(quizPublicId)
                .title(title)
                .content(content)
                .choices(choices)
                .releaseAt(releaseAt)
                .build();
    }
}
