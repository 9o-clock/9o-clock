package dreamdiary.support.cache;

import org.springframework.util.StringUtils;

public record CacheKey(
        String value
) {
    public CacheKey {
        if (!StringUtils.hasText(value)) throw CacheException.invalidFormat();
    }
}
