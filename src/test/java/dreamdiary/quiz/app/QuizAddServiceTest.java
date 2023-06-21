package dreamdiary.quiz.app;

import dreamdiary.quiz.domain.event.QuizGeneratedEvent;
import dreamdiary.quiz.domain.model.Choice;
import dreamdiary.quiz.domain.model.QuizException;
import dreamdiary.quiz.domain.model.QuizPublicId;
import dreamdiary.quiz.helper.QuizTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuizAddServiceTest extends QuizTestHelper {
    @InjectMocks
    private QuizAddService quizAddService;

    @DisplayName("퀴즈 공개키 생성 요청")
    @Test
    void addQuiz_call_obtainPublicId_to_repository() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();

        quizAddService.addQuiz(givenRequest);

        verify(mockQuizRepository, times(1)).obtainQuizPublicId();
    }

    @DisplayName("동일한 제목이 존재하면 예외처리")
    @Test
    void addQuiz_throw_alreadyExistsTitle_exception() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();
        BDDMockito.given(mockQuizRepository.isTitleAlreadyExists(any())).willReturn(true);

        final QuizException exception = Assertions.assertThrows(QuizException.class, () ->
                quizAddService.addQuiz(givenRequest));
        assertThat(exception.getMessage()).isEqualTo(QuizException.duplicatedTitleExists().getMessage());
    }

    @DisplayName("퀴즈 생성 성공 이벤트 발행")
    @Test
    void addQuiz_publish_quiz_generated_event() {
        final QuizAddRequest givenRequest = anQuizAddRequest().build();
        final String givenObtainId = "OBTAIN_ID";
        BDDMockito.given(mockQuizRepository.obtainQuizPublicId()).willReturn(new QuizPublicId(givenObtainId));

        quizAddService.addQuiz(givenRequest);

        verify(mockPublisher, times(1)).publishEvent(publishEventCaptor.capture());
        assertThat(publishEventCaptor.getValue()).isNotNull();
        final QuizGeneratedEvent event = (QuizGeneratedEvent) publishEventCaptor.getValue();
        assertThat(event.quiz().getTitle().value()).isEqualTo(givenRequest.getTitle());
        assertThat(event.quiz().getContent().value()).isEqualTo(givenRequest.getContent());
        assertThat(event.quiz().getReleaseAt()).isEqualTo(givenRequest.getReleaseAt());
        assertThat(event.quiz().getQuizPublicId().value()).isEqualTo(givenObtainId);

        int i = 0;
        for (final Choice choice : event.quiz().getChoices().values()) {
            assertThat(choice.value()).isEqualTo(givenRequest.getChoices().get(i));
            i++;
        }
    }
}
