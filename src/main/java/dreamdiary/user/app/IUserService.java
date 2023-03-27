package dreamdiary.user.app;

import dreamdiary.user.domain.UserDto;

public interface IUserService {
    public UserDto register(UserDto userDto);
}
