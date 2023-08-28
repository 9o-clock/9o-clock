package dreamdiary.nineoclock.infrastructure.quiz;

import dreamdiary.member.domain.model.MemberPublicId;
import dreamdiary.member.domain.port.MemberPort;
import dreamdiary.quiz.domain.model.SubmitterUniqId;
import dreamdiary.quiz.domain.model.exception.SubmitterNotFoundException;
import dreamdiary.quiz.domain.port.SubmitterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class SubmitterAdapter implements SubmitterPort {
    private final MemberPort memberPort;
    @Override
    public SubmitterUniqId verify(final String submitterPublicId) {
        final Long submitterUniqId = memberPort.findMemberUniqIdBy(new MemberPublicId(submitterPublicId))
                .orElseThrow(SubmitterNotFoundException::new);
        return new SubmitterUniqId(submitterUniqId);
    }
}
