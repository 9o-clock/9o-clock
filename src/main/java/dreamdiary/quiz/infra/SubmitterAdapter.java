package dreamdiary.quiz.infra;

import dreamdiary.quiz.domain.model.SubmitterUniqId;
import dreamdiary.quiz.domain.port.SubmitterPort;
import org.springframework.stereotype.Component;

@Component
class SubmitterAdapter implements SubmitterPort {
    @Override
    public SubmitterUniqId verify(final String submitterPublicId) {
        return new SubmitterUniqId(1L);
    }
}
