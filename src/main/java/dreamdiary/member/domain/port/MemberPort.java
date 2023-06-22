package dreamdiary.member.domain.port;

import dreamdiary.member.domain.model.Member;
import dreamdiary.member.domain.model.MemberPublicId;

import java.util.Optional;

public interface MemberPort {
    Optional<Member> findBy(final MemberPublicId id);
    void save(final Member member);
}
