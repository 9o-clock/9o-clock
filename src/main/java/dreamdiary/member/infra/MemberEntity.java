package dreamdiary.member.infra;


import dreamdiary.member.domain.MemberException;
import dreamdiary.member.domain.model.Member;
import dreamdiary.member.domain.model.MemberPublicId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER", indexes = {
        @Index(name = "IDX_MEMBER_PUBLIC_ID", columnList = "public_id")
})
@Entity
@Getter
class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = MemberPublicIdConverter.class)
    @Column(name = "public_id", nullable = false, unique = true)
    private MemberPublicId publicId;
    @Column(name = "nickname", nullable = false)
    private String nickname;

    MemberEntity(final Member member) {
        if (null == member) throw MemberException.invalidFormat();
        this.publicId = member.getPublicId();
        this.nickname = member.getNickname();
    }

    public Member toMember() {
        return Member.builder()
                .publicId(this.publicId)
                .nickname(this.nickname)
                .build();
    }
}
