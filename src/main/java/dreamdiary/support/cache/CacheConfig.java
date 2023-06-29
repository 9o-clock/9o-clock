package dreamdiary.support.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
class CacheConfig {
    private static final long DURATION = 10L;
    private static final long MAXIMUM_SIZE = 500L;

    @Bean
    CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(MAXIMUM_SIZE)
                .expireAfterAccess(DURATION, TimeUnit.MINUTES);
//                .weakKeys() // 깊은 값 참조 비교에서 얕은 값 참조 비교로 변경
//                .recordStats(); // 캐싱 내역 통계 (사용 시 부가 기능이 동작하므로 성능 저하)
    }
}


