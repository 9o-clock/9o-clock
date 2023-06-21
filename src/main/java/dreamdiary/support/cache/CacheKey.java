package dreamdiary.support.cache;

import org.springframework.util.StringUtils;

import java.io.Serializable;

public record CacheKey(String value) implements Serializable {
    public CacheKey {
        if (!StringUtils.hasText(value)) throw CacheException.invalidFormat();
    }
}
