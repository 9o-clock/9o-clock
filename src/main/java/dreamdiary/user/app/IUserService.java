package dreamdiary.user.app;

import dreamdiary.user.domain.UserDto;

import javax.servlet.http.HttpSession;

public interface IUserService {
    public UserDto register(UserDto userDto);

    public UserDto login(String loginId, String password, HttpSession session);
}
