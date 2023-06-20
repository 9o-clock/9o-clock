package dreamdiary.support.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface CacheStore {
    void storeData(CacheKey cacheKey, Object data, long timeout, TimeUnit timeUnit);
    void storeMap(final CacheKey cacheKey, final Map<Object, Object> data, final long timeout, final TimeUnit timeUnit);
    <T> Optional<T> findData(CacheKey cacheKey);
    Map<Object, Object> findMap(final CacheKey cacheKey);
    Boolean removeKey(CacheKey cacheKey);
    Boolean setExpire(CacheKey cacheKey, long timeout, TimeUnit timeUnit);
}
