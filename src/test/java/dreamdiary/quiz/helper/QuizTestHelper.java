package dreamdiary.quiz.helper;

import dreamdiary.quiz.app.QuizAddRequest;
import dreamdiary.quiz.app.QuizSubmitRequest;
import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.Choices;
import dreamdiary.quiz.domain.model.Quiz;
import dreamdiary.quiz.domain.model.QuizContent;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.domain.model.QuizTitle;
import dreamdiary.quiz.domain.port.QuizPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public abstract class QuizTestHelper {
    @Mock
    protected QuizPort mockQuizPort;
    @Mock
    protected ApplicationEventPublisher mockPublisher;
    @Captor
    protected ArgumentCaptor<QuizAddRequest> quizAddRequestCaptor;
    @Captor
    protected ArgumentCaptor<Object> publishEventCaptor;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(mockQuizPort.isTitleAlreadyExists(any())).thenReturn(false);
        Mockito.lenient().when(mockQuizPort.findBy((QuizPublicId) any())).thenReturn(Optional.of(anQuiz().build()));
        Mockito.lenient().when(mockQuizPort.obtainQuizPublicId()).thenReturn(new QuizPublicId(UUID.randomUUID().toString()));

    }

    protected Quiz.QuizBuilder anQuiz() {
        final LocalDate date = LocalDate.now().minusDays(1L);
        return Quiz.builder()
                .quizPublicId(new QuizPublicId(UUID.randomUUID().toString()))
                .title(new QuizTitle("Quiz Title"))
                .content(new QuizContent("Quiz Content"))
                .choices(new Choices(List.of(new Choice("CHOICE_UNIQ_ID", "강아지"), new Choice("고양이"))))
                .releaseAt(date.atTime(14, 0))
                .answerReleaseAt(date.atTime(16, 30))
                ;
    }

    protected QuizAddRequest.QuizAddRequestBuilder anQuizAddRequest() {
        return QuizAddRequest.builder()
                .title("givenTitle")
                .content("givenContent")
                .choices(List.of("A", "B", "C"))
                .releaseAt(LocalDate.now().plusDays(5L).atTime(14, 0))
                .answerReleaseAt(LocalDate.now().plusDays(5L).atTime(16, 30));
    }

    protected QuizSubmitRequest.QuizSubmitRequestBuilder anQuizSubmitRequest() {
        return QuizSubmitRequest.builder()
                .choicePublicId("CHOICE_UNIQ_ID");
    }
}
