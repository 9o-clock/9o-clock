package dreamdiary.nineoclock.application.quiz.port.inbound;

import java.time.LocalDateTime;
import java.util.List;

public record QuizAddCommand(String title,
                             String content,
                             List<String> choices,
                             LocalDateTime releaseAt,
                             LocalDateTime answerReleaseAt) {
}
