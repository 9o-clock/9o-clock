package dreamdiary.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private LocalDateTime registerDate;
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
