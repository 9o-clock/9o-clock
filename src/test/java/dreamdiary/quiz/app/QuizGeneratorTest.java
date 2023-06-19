
package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.model.Quiz;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuizGeneratorTest extends QuizTestHelper {
    @InjectMocks
    private QuizGenerator quizGenerator;

    @Test
    void generateQuiz_returnValue() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();

        final Quiz quiz = quizGenerator.generateQuiz(givenRequest);

        assertThat(givenRequest.getTitle()).isEqualTo(quiz.getTitle().value());
        assertThat(givenRequest.getContent()).isEqualTo(quiz.getContent().value());
        assertThat(givenRequest.getReleaseAt()).isEqualTo(quiz.getReleaseAt());
        for (int i = 0; i <givenRequest.getChoices().size(); i++) {
            assertThat(givenRequest.getChoices().get(i)).isEqualTo(quiz.getChoices().values().get(i).text());
        }
    }

    @Test
    void generateQuiz_call_obtainId_to_repository() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();

        final Quiz quiz = quizGenerator.generateQuiz(givenRequest);

        verify(mockQuizRepository, times(1)).obtainQuizPublicId();
        assertThat(quiz.getQuizPublicId()).isEqualTo(mockQuizRepository.obtainQuizPublicId());
    }
}
