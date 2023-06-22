package dreamdiary.member.domain.model;

import dreamdiary.member.domain.MemberException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public record MemberPublicId(
        String value
) implements Serializable {
    public MemberPublicId {
        if (!StringUtils.hasText(value)) throw MemberException.invalidFormat();
    }
}
