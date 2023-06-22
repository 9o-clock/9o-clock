package dreamdiary.member.infra;

import dreamdiary.member.domain.model.MemberPublicId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPublicId(final MemberPublicId publicId);
}
