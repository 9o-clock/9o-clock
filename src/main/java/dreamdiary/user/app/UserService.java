package dreamdiary.user.app;

import dreamdiary.support.advice.exception.RequestException;
import dreamdiary.user.domain.User;
import dreamdiary.user.domain.UserDto;
import dreamdiary.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDto register(UserDto userDto) {
        // 중복 검사
        List<User> userList = userRepository.findByLoginId(userDto.getLoginId());
        if(userList.size() != 0) {
            throw RequestException.of(HttpStatus.BAD_REQUEST,"UserDto.loginId is already registered");
        }

        User result = userRepository.save(userDto.toEntity());

        return new UserDto(result);
    }

    @Override
    public UserDto login(String loginId, String password, HttpSession session) {
        List<User> userList = userRepository.findByLoginId(loginId);
        if(userList.size() == 0) {
            throw RequestException.of(HttpStatus.BAD_REQUEST,String.format("User not found (loginId=%s)", loginId));
        }
        
        // loginId는 유니크 값이므로, 유저는 반드시 1명
        User user = userList.get(0);
        String userPassword = user.getPassword();

        // @TODO: 비밀번호 암호화 및 검증 로직 추가
        if (!userPassword.equals(password)) {
            throw RequestException.of(HttpStatus.BAD_REQUEST,String.format("User not found (loginId=%s)", loginId));
        }

        session.setAttribute("LOGINID", user.getLoginId());
        session.setAttribute("NICKNAME", user.getNickname());

        return new UserDto(user);
    }
}
