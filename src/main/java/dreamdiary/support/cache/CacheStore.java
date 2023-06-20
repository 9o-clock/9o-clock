package dreamdiary.support.cache;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface CacheStore {
    void storeData(CacheKey cacheKey, Object data, long timeout, TimeUnit timeUnit);
    <T> Optional<T> findData(CacheKey cacheKey);
    Boolean removeData(CacheKey cacheKey);
    Boolean setExpire(CacheKey cacheKey, long timeout, TimeUnit timeUnit);
}
