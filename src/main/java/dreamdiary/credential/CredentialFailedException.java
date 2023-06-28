package dreamdiary.credential;

class CredentialFailedException extends RuntimeException {
    private static final long serialVersionUID = 8870167771885318012L;

    private CredentialFailedException(final String message) {
        super(message);
    }

    static CredentialFailedException memberCredentialFailed() {
        return new CredentialFailedException("회원 인증 정보가 확인되지 않습니다.");
    }
}
