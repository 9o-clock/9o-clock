package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.Choices;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizContent;
import dreamdiary.quiz.domain.model.QuizTitle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class QuizGenerator {
    Quiz toQuiz(final QuizAddRequest request) {
        // TODO 존재하지 않는 식별키를 달라고 port로 요청을 해야할지, 저장하면 아싸리 내부에서 그렇게 해야하는건지..
        // TODO port는 단순 외부 자원을 활용하는 것이고, 식별값은 요구사항이니 여기서(app,domain 패키지) 시작해야는게 맞는 것 같음.
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
