package dreamdiary.nineoclock.application.quiz.port.outbound;

import dreamdiary.nineoclock.domain.quiz.model.SubmitterUniqId;

public interface SubmitterOutPort {
    SubmitterUniqId verify(final String submitterPublicId);
}
