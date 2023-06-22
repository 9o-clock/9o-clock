package dreamdiary.member.domain;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private static final long serialVersionUID = -8702997881369081319L;

    private MemberException(final String message) {
        super(message);
    }

    public static MemberException invalidFormat() {
        return new MemberException("회원 형식이 잘못되었습니다.");
    }
}
