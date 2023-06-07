package dreamdiary.user.app;

import dreamdiary.user.domain.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    // 로그인 아이디 검증 -> 문자 제한 : 영단어(대소문자), 숫자 / 길이 제한 : 5 ~ 20자
    private static final String LOGIN_ID_PATTERN = "^[a-zA-Z0-9]{5,20}$";
    // 비밀번호 검증 -> 문자 제한 : 대문자, 특수문자 각각 최소 한 글자 포함, 숫자 / 길이 제한 : 8 ~ 25자
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=\\S+$)(?=.*[!@#$%^&*()_+-=\\[\\]{};':\\\"\\|,.<>?]).{5,20}$";
    // 닉네임 검증 -> 문자 제한 : 한글, 영단어(대소문자), 숫자 / 길이 제한 : 1 ~ 10자
    private static final String NICKNAME_PATTERN = "^[a-zA-Z0-9가-힣]{1,10}$";

    private final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private final Pattern loginIdPattern = Pattern.compile(LOGIN_ID_PATTERN);
    private final Pattern nicknamePattern = Pattern.compile(NICKNAME_PATTERN);

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "user.loginId.empty", "로그인 아이디를 입력해주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "비밀번호를 입력해주세요");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "user.nickname.empty", "닉네임을 입력해주세요");

        UserDto userDto = (UserDto) target;

        if (!loginIdPattern.matcher(userDto.getLoginId()).matches()) {
            errors.rejectValue("loginId", "UserDto.loginId.invalid");
        }
        if (!passwordPattern.matcher(userDto.getPassword()).matches()) {
            errors.rejectValue("password", "UserDto.password.invalid");
        }
        if (!nicknamePattern.matcher(userDto.getNickname()).matches()) {
            errors.rejectValue("nickname", "UserDto.nickname.invalid");
        }
    }
}
