package dreamdiary.member.domain.model;

import dreamdiary.member.domain.MemberException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member {
    private MemberPublicId publicId;
    private String nickname;

    @Builder
    private Member(final MemberPublicId publicId, final String nickname) {
        if (null == publicId || !StringUtils.hasText(nickname)) throw MemberException.invalidFormat();
        this.publicId = publicId;
        this.nickname = nickname;
    }
}
