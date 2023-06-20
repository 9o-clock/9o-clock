package dreamdiary.quiz.helper;

import dreamdiary.quiz.app.QuizAddRequest;
import dreamdiary.quiz.app.QuizSubmitRequest;
import dreamdiary.quiz.domain.QuizRepository;
import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.Choices;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizContent;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public abstract class QuizTestHelper {
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
        Mockito.lenient().when(mockQuizRepository.isTitleAlreadyExists(any())).thenReturn(false);
        Mockito.lenient().when(mockQuizRepository.findBy((QuizPublicId) any())).thenReturn(Optional.of(anQuiz().build()));
        Mockito.lenient().when(mockQuizRepository.obtainQuizPublicId()).thenReturn(new QuizPublicId(UUID.randomUUID().toString()));

    }

    protected Quiz.QuizBuilder anQuiz() {
        return Quiz.builder()
                .quizPublicId(new QuizPublicId(UUID.randomUUID().toString()))
                .title(new QuizTitle("Quiz Title"))
                .content(new QuizContent("Quiz Content"))
                .choices(new Choices(List.of(new Choice("강아지"), new Choice("고양이"))))
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

    protected QuizSubmitRequest.QuizSubmitRequestBuilder anQuizSubmitRequest() {
        return QuizSubmitRequest.builder()
                .choiceId(1L);
    }
}
