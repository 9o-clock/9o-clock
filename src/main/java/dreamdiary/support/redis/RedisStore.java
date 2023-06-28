package dreamdiary.support.redis;

import dreamdiary.support.cache.CacheException;
import dreamdiary.support.cache.CacheKey;
import dreamdiary.support.cache.CacheStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
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
    public void storeMap(final CacheKey cacheKey, final Map<Object, Object> data, final long timeout, final TimeUnit timeUnit) {
        if (null == data) throw CacheException.invalidFormat();
        data.forEach((key, value) -> {
            redisTemplate.opsForHash().put(cacheKey, key, value);
        });
        this.setExpire(cacheKey, timeout, timeUnit);
    }

    @Override
    public Map<Object, Object> findMap(final CacheKey cacheKey) {
        return redisTemplate.opsForHash().entries(cacheKey);
    }

    @Override
    public Optional<Object> findData(final CacheKey cacheKey) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(cacheKey));
    }

    @Override
    public Boolean removeKey(final CacheKey cacheKey) {
        return redisTemplate.delete(cacheKey);
    }

    @Override
    public Boolean setExpire(final CacheKey cacheKey, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(cacheKey, timeout, timeUnit);
    }
}
