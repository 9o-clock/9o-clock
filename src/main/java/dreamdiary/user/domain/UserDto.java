package dreamdiary.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("loginId")
    private String loginId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("nickname")
    private String nickname;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
    private LocalDateTime registerDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
    private LocalDateTime updatedDate;

    public UserDto() {
        this.registerDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public UserDto(User entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.nickname = entity.getNickname();
        this.registerDate = entity.getRegisterDate();
        this.updatedDate = entity.getUpdatedDate();
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .registerDate(this.registerDate)
                .updatedDate(this.updatedDate)
                .build();
    }
}
