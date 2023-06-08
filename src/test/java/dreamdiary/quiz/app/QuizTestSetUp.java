package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.Choice;
import dreamdiary.quiz.domain.Choices;
import dreamdiary.quiz.domain.Quiz;
import dreamdiary.quiz.domain.QuizContent;
import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.QuizTitle;
import dreamdiary.quiz.domain.QuizWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class QuizTestSetUp {
    @Mock
    protected QuizGenerator mockQuizGenerator;
    @Mock
    protected QuizRepository mockQuizRepository;

    @Captor
    protected ArgumentCaptor<QuizAddRequest> quizAddRequestCaptor;

    @BeforeEach
    void setUp() {
        BDDMockito.given(mockQuizGenerator.toQuiz(any())).willReturn(anQuiz().build());
        BDDMockito.given(mockQuizRepository.isTitleAlreadyExists(any())).willReturn(false);
    }

    protected Quiz.QuizBuilder anQuiz() {
        return Quiz.builder()
                .writer(new QuizWriter("writer"))
                .title(new QuizTitle("Quiz Title"))
                .content(new QuizContent("Quiz Content"))
                .choices(new Choices(new Choice("강아지"), new Choice("고양이")))
                .releaseAt(LocalDateTime.now().plusDays(1L))
                ;
    }
}
