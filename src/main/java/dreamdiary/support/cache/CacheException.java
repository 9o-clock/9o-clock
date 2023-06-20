package dreamdiary.support.cache;

import lombok.Getter;

@Getter
public class CacheException extends RuntimeException {
    private static final long serialVersionUID = 6884439666591897495L;

    private CacheException(final String message) {
        super(message);
    }

    public static CacheException invalidFormat() {
        return new CacheException("형식이 잘못되었습니다.");
    }
}
