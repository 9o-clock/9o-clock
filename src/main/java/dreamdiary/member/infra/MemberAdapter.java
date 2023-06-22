package dreamdiary.member.infra;

import dreamdiary.member.domain.model.Member;
import dreamdiary.member.domain.model.MemberPublicId;
import dreamdiary.member.domain.port.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class MemberAdapter implements MemberPort {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public Optional<Member> findBy(final MemberPublicId id) {
        return memberEntityRepository.findByPublicId(id).map(MemberEntity::toMember);
    }

    @Override
    public void save(final Member member) {
        memberEntityRepository.save(new MemberEntity(member));
    }
}
