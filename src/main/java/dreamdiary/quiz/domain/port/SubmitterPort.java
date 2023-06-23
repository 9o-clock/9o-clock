package dreamdiary.quiz.domain.port;

import dreamdiary.quiz.domain.model.SubmitterUniqId;

public interface SubmitterPort {
    SubmitterUniqId verify(final String submitterPublicId);
}
