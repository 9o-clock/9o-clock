package dreamdiary.support.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RedisData<T extends Serializable> implements Serializable {
    private String searchCode;
    private T data;
    private LocalDateTime createdAt;

    public RedisData(String searchCode, T data, LocalDateTime createdAt) {
        this.searchCode = searchCode;
        this.data = data;
        this.createdAt = createdAt;
    }

    public static class RedisDataBuilder<BT extends Serializable> {
        private String searchCode;
        private BT data;
        private LocalDateTime createAt;

        public RedisDataBuilder<BT> setSearchCode(String code) {
            this.searchCode = code;
            return this;
        }

        public RedisDataBuilder<BT> setData(BT data) {
            this.data = data;
            return this;
        }

        public RedisDataBuilder<BT> setCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public RedisData<BT> build() {
            return new RedisData<>(this.searchCode, this.data, this.createAt);
        }
    }
}
