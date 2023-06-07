package dreamdiary.user.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId; // 문자 제한 : 영단어(대소문자), 숫자 / 길이 제한 : 5 ~ 20자

    @Column(nullable = false)
    private String password; // 문자 제한 : 대문자, 특수문자 각각 최소 한 글자 포함, 숫자 / 길이 제한 : 8 ~ 25자

    @Column(nullable = false)
    private String nickname; // 문자 제한 : 한글, 영단어(대소문자), 숫자 / 길이 제한 : 1 ~ 10자

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime registerDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
