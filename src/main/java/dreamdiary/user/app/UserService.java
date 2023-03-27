package dreamdiary.user.app;

import dreamdiary.support.advice.exception.RequestException;
import dreamdiary.user.domain.User;
import dreamdiary.user.domain.UserDto;
import dreamdiary.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDto register(UserDto userDto) {
        // 중복 검사
        List<User> userList = userRepository.findByLoginId(userDto.getLoginId());
        if (userList.size() != 0) {
            throw RequestException.of(HttpStatus.BAD_REQUEST,"UserDto.loginId is already registered");
        }

        User result = userRepository.save(userDto.toEntity());

        return new UserDto(result);
    }
}
