
package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.Quiz;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class QuizGeneratorTest extends QuizTestHelper {
    private final QuizGenerator quizGenerator = new QuizGenerator();

    @Test
    void toQuiz_returnValue() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();

        final Quiz quiz = quizGenerator.toQuiz(givenRequest);

        assertThat(givenRequest.getTitle()).isEqualTo(quiz.getTitle().getValue());
        assertThat(givenRequest.getContent()).isEqualTo(quiz.getContent().getValue());
        assertThat(givenRequest.getReleaseAt()).isEqualTo(quiz.getReleaseAt());
        for (int i = 0; i <givenRequest.getChoices().size(); i++) {
            assertThat(givenRequest.getChoices().get(i)).isEqualTo(quiz.getChoices().getValues().get(i).getText());
        }
    }
}