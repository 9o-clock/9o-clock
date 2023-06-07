package dreamdiary.user.api;

import dreamdiary.user.app.IUserService;
import dreamdiary.user.domain.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApi {

    private final IUserService userService;

    @PostMapping("login")
    public String login(@RequestBody Map<String, String> param, HttpSession session) {
        String loginId = param.get("loginId");
        String password = param.get("password");

        log.info("[USER] 로그인 요청 // loginId={}, password={}", loginId, password);

        // @TODO: 로그인 처리 방법 확인 (스프링 시큐리티 적용 등..)
        UserDto userDto = userService.login(loginId, password, session);

        return userDto.toString();
    }
}
