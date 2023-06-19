package dreamdiary.quiz.infra;

import dreamdiary.quiz.app.QuizTestHelper;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizTitle;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuizAdaptorTest extends QuizTestHelper {
    @InjectMocks
    private QuizAdaptor quizAdaptor;
    @Mock
    private QuizEntityRepository mockQuizEntityRepository;
    @Captor
    private ArgumentCaptor<String> titleCaptor;
    @Captor
    private ArgumentCaptor<QuizEntity> quizEntityCaptor;

    @Test
    void isTitleAlreadyExists_returnValue() {
        final QuizTitle givenTitle = new QuizTitle("givenTitle");
        BDDMockito.given(mockQuizEntityRepository.existsByTitle(eq(givenTitle.value()))).willReturn(true);

        boolean result = quizAdaptor.isTitleAlreadyExists(givenTitle);

        verify(mockQuizEntityRepository, times(1)).existsByTitle(titleCaptor.capture());
        assertThat(titleCaptor.getValue()).isNotNull();
        assertThat(titleCaptor.getValue()).isEqualTo(givenTitle.value());
        assertThat(result).isTrue();
    }

    @Test
    void store_passes_quizEntity_to_save_of_quizEntityRepository() {
        final Quiz givenQuiz = anQuiz().build();

        quizAdaptor.store(givenQuiz);

        verify(mockQuizEntityRepository, times(1)).save(quizEntityCaptor.capture());
        assertThat(quizEntityCaptor.getValue()).isNotNull();
        assertThat(quizEntityCaptor.getValue().getTitle()).isEqualTo(givenQuiz.getTitle().value());
        assertThat(quizEntityCaptor.getValue().getContent()).isEqualTo(givenQuiz.getContent().value());
        assertThat(quizEntityCaptor.getValue().getReleaseAt()).isEqualTo(givenQuiz.getReleaseAt());
    }
}
