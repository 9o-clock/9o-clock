package dreamdiary.support.redis;

import dreamdiary.support.cache.CacheKey;
import dreamdiary.support.cache.CacheStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
class RedisStore implements CacheStore {
    private final RedisTemplate<CacheKey, Object> redisTemplate;

    @Override
    public void storeData(final CacheKey cacheKey, final Object data, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(cacheKey, data, timeout, timeUnit);
    }

    @Override
    public <T> Optional<T> findData(final CacheKey cacheKey) {
        Object value = redisTemplate.opsForValue().get(cacheKey);
        if (null == value) {
            return Optional.empty();
        }
        try {
            return Optional.of((T) value);
        } catch (ClassCastException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean removeData(final CacheKey cacheKey) {
        return redisTemplate.delete(cacheKey);
    }

    @Override
    public Boolean setExpire(final CacheKey cacheKey, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(cacheKey, timeout, timeUnit);
    }
}
