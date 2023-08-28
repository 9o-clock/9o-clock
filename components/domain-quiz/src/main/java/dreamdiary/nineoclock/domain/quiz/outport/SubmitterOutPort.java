package dreamdiary.nineoclock.domain.quiz.outport;

import dreamdiary.nineoclock.domain.quiz.model.SubmitterUniqId;

public interface SubmitterOutPort {
    SubmitterUniqId verify(final String submitterPublicId);
}
