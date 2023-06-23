package dreamdiary.member.domain.port;

import dreamdiary.member.domain.model.Member;
import dreamdiary.member.domain.model.MemberPublicId;

import java.util.Optional;

public interface MemberPort {
    Optional<Long> findMemberUniqIdBy(final MemberPublicId id);
    Optional<Member> findBy(final MemberPublicId id);
    void save(final Member member);
}
