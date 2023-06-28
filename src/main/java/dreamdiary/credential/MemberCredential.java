package dreamdiary.credential;

import org.springframework.util.StringUtils;

public record MemberCredential(
        String memberPublicId
) {
    public MemberCredential {
        if (!StringUtils.hasText(memberPublicId)) throw CredentialFailedException.memberCredentialFailed();
    }
}
