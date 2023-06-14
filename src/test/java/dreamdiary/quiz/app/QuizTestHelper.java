package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class QuizTestHelper {
    @Mock
    protected QuizGenerator mockQuizGenerator;
    @Mock
    protected QuizRepository mockQuizRepository;
    @Mock
    protected ApplicationEventPublisher mockPublisher;
    @Captor
    protected ArgumentCaptor<QuizAddRequest> quizAddRequestCaptor;
    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @BeforeEach
    void setUp() {
        BDDMockito.lenient().when(mockQuizGenerator.toQuiz(any())).thenReturn(anQuiz().build());
        BDDMockito.lenient().when(mockQuizRepository.isTitleAlreadyExists(any())).thenReturn(false);
    }

    protected Quiz.QuizBuilder anQuiz() {
        return Quiz.builder()
                .title(new QuizTitle("Quiz Title"))
                .content(new QuizContent("Quiz Content"))
                .choices(new Choices(new Choice("강아지"), new Choice("고양이")))
                .releaseAt(LocalDateTime.now().plusDays(1L))
                ;
    }

    protected QuizAddRequest.QuizAddRequestBuilder anQuizAddRequest() {
        return QuizAddRequest.builder()
                .title("givenTitle")
                .content("givenContent")
                .choices(List.of("A", "B", "C"))
                .releaseAt(LocalDate.now().plusDays(5L).atTime(14, 0));
    }
}
